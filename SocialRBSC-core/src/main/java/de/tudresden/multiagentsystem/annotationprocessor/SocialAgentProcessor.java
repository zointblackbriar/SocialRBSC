/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.annotationprocessor;

import com.google.auto.service.AutoService;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;

import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionSocialAgent;
import de.tudresden.socialrbscdeterministic.mas.annotation.PreconditionVariable;
import de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgent;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.*;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;


/**
 * This is going to be used for Social Agent BDI Reasoning.
 *
 * @author Orcun Oruc
 */
// for now we focus on three annotations in social agent concept

// @SupportedAnnotationTypes(
// {
// "de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentBelief",
// "de.tudresden.socialrbscdeterministic.mas.annotation.SocialAgentDefinition",
// "de.tudresden.socialrbscdeterministic.mas.annotation.SocialGoal",
// }
// )
@AutoService(Processor.class)
@SupportedAnnotationTypes("de.tudresden.socialrbscdeterministic.mas.annotation.*")

// @SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class SocialAgentProcessor extends AbstractProcessor {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(SocialAgentProcessor.class.getName());

    //~ Constructors -------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new {@link SocialAgentProcessor} object.
     */
    public SocialAgentProcessor() {
    }

    /**
     * Creates a new {@link SocialAgentProcessor} object.
     *
     * @param mockLogger            TODO DOCUMENT ME!
     * @param mockSolidityGenerator TODO DOCUMENT ME!
     */
    public SocialAgentProcessor(Logger mockLogger, SolidityGenerator mockSolidityGenerator) {
        logger.info("mockLogger: " + mockLogger);
        logger.info("mockSolidityGenerator: " + mockSolidityGenerator);
    }

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     *
     * @param  annotations TODO DOCUMENT ME!
     * @param  roundEnv    TODO DOCUMENT ME!
     *
     * @return TODO DOCUMENT ME!
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.size() == 0) {
            return false;
        }

        Set<? extends Element> socialAgentElements = roundEnv.getElementsAnnotatedWith(SocialAgent.class);
        Set<? extends Element> socialAgentPreconditionSocialAgent = roundEnv.getElementsAnnotatedWith(
                PreconditionSocialAgent.class);

        try {
            for (Element element : socialAgentElements) {
                SocialAgent socialAgentElement = element.getAnnotation(SocialAgent.class);
                String effectiveAgentName = resolveAgentName(socialAgentElement);

                if ((element.getKind() == ElementKind.CLASS) &&
                        (effectiveAgentName != null) && !effectiveAgentName.isBlank()) {
                    // Then we can understand there is a social agent definition.
                    String nameOfEntity = element.getSimpleName().toString();
                    logger.info("Social agent name in SocialAgentAnnotationProcessor: " + nameOfEntity);

                    if ((nameOfEntity != null) && (socialAgentElement.addRole() == false) &&
                            (socialAgentElement.removeRole() == false)) {
                        SolidityGenerator.soliditySocialAgentGeneration(nameOfEntity, effectiveAgentName);
                        logger.info("Social Agent agentName has been generated");
                    } else if ((nameOfEntity != null) && socialAgentElement.addRole()) {
                        SolidityGenerator.soliditySocialAddRole(nameOfEntity);
                        logger.info("Social Agent add role has been generated");
                    } else if ((nameOfEntity != null) && socialAgentElement.removeRole()) {
                        SolidityGenerator.soliditySocialAgentRemoveRole(nameOfEntity);
                        logger.info("Social Agent remove role has been generated");
                    }
                }

                TypeElement classElement = (TypeElement) element;

                List<? extends Element> getEnclosedElements = element.getEnclosedElements();

                logger.info("classElement:" + classElement);
                logger.info("Processing class for PreconditionVariable: " + classElement.getSimpleName());

                for (Element enclosedElement : getEnclosedElements) {
                    if ((enclosedElement.getKind() == ElementKind.METHOD) && (enclosedElement instanceof ExecutableElement)) {
                        // Cast the element to ExecutableElement which represents the method
                        ExecutableElement methodElement = (ExecutableElement) enclosedElement;

                        // Log or process the method
                        logger.info("Found method: " + methodElement.getSimpleName());
                    }
                }

                // Get all fields with @PreconditionVariable in the class
                for (VariableElement field : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
                    if (field.getAnnotation(PreconditionVariable.class) != null) {
                        logger.info("Field: " + field.getSimpleName() + "in class: " + classElement.getSimpleName());
                    }
                }

                logger.info("Social Agent contract has been generated");
            }
        } catch (Exception ex) {
            logger.severe("Generated an expection: " + ex);

            return false;
        }

        return true;
    }

    private String resolveAgentName(SocialAgent socialAgent) {
        if (socialAgent == null) {
            return "";
        }
        if (socialAgent.agentName() != null && !socialAgent.agentName().isBlank()) {
            return socialAgent.agentName();
        }
        return socialAgent.name();
    }
}
