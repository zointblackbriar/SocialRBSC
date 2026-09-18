/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.annotationprocessor;

import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.NaturalType;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;


/**
 * Role Annotation processor for the package named as de.tudresden.socialrbscdeterministic.rolecompartment.
 *
 * @author Orcun Oruc
 */
// @SupportedAnnotationTypes(
// {
// "de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role",
// "de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment",
// "de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Player"
// }
// )
@AutoService(Processor.class)
@SupportedAnnotationTypes("de.tudresden.socialrbscdeterministic.rolecompartment.annotation.*")

// @SupportedSourceVersion(SourceVersion.RELEASE_11)
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class RoleProcessor extends AbstractProcessor {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private static String roleName;

    /**
     * TODO DOCUMENT ME!
     */
    private static final Logger logger = Logger.getLogger(RoleProcessor.class.getName());

    //~ Constructors -------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new {@link RoleProcessor} object.
     */
    public RoleProcessor() {
        // No-arg constructor
    }

    /**
     * Creates a new {@link RoleProcessor} object.
     *
     * @param mockLogger            TODO DOCUMENT ME!
     * @param mockSolidityGenerator TODO DOCUMENT ME!
     */
    public RoleProcessor(Logger mockLogger, SolidityGenerator mockSolidityGenerator) {
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
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // checking for the number of annotations.
        if (annotations.size() == 0) {
            return false;
        }

        try {
            // Scan the Role annotation
            Set<? extends Element> roleTypeElements = roundEnv.getElementsAnnotatedWith(Role.class);
            Set<? extends Element> naturalTypeElements = roundEnv.getElementsAnnotatedWith(NaturalType.class);

            for (Element element : naturalTypeElements) {
                if (roleTypeElements.contains(element)) {
                    errorThrows("@NaturalType and @RoleType cannot coexist on the same class", element);

                    return false;
                }
            }

            // Scanning roles
            for (Element element : roleTypeElements) {
                Role roleElement = element.getAnnotation(Role.class);
                System.out.println("roleElement: " + roleElement);

                if (element.getKind() == ElementKind.CLASS) {
                    roleName = element.getSimpleName().toString();
                    logger.info("Name of the contract for static role in solidity: " + element
                                .getSimpleName().toString());
                    SolidityGenerator.solidityForSocialRbSCRoleGenerateOnlyRole(roleName);
                    infoThrows("Solidity-specific Role has been generated from the annotation processor element: ", element);
                }

                // We need to assign RoleMethod annotation for subelements
                for (Element subElement : element.getEnclosedElements()) {
                    // sub elements such as methods will be added to annotation processing
                    if (subElement.getKind() == ElementKind.METHOD) { // if the method based annotations
                        logger.info("getEnclosedElement: " + subElement.getSimpleName().toString());
                    }

                    if (subElement.getKind() == ElementKind.OTHER) {
                        // this is the element that we are not looking for.
                        // do nothing for now
                        logger.info("OTHER ELEMENTS");

                        continue;
                    }

                    logger.info("elements listed: " + subElement.getKind().toString());
                    // SolidityGenerator.solidityForSocialRbSCRoleGenerate(roleName, methodNames, stateVariableNames,
                    // roleName);
                }
            }

            // Scan the RoleMethod annotation
            Set<? extends Element> roleMethodElements = roundEnv.getElementsAnnotatedWith(RoleMethod.class);

            for (Element element : roleMethodElements) {
                // RoleMethod roleMethod = element.getAnnotation(RoleMethod.class);

                if (element.getKind() == ElementKind.METHOD) {
                    String roleMethodName = element.getSimpleName().toString();

                    // Role Method function generator
                    SolidityGenerator.solidityForRoleMethod(roleMethodName, roleMethodName, roleName);
                }
            }

            // Scanning compartment annotation
            Set<? extends Element> compartmentElements = roundEnv.getElementsAnnotatedWith(Compartment.class);

            for (Element element : compartmentElements) {
                Compartment compartmentElement = element.getAnnotation(Compartment.class);
                System.out.println("Compartment element: " + compartmentElement);

                // element.getSimpleName().toString().equalsIgnoreCase("Compartment") if you need to name of the annotated element
                if (element.getKind().isInterface()) {
                    String interfaceName = element.getSimpleName().toString();
                    SolidityGenerator.solidityForCompartmentCreation(interfaceName, interfaceName);
                    infoThrows("Solidity-specific Compartment has been generated from the annotation processor element: ",
                        element);
                }
            }

            return true;
        } catch (Exception ex) {
            logger.severe("An exception occurred during processing: {}" + ex.getMessage());
        }

        return false;
    }

    /**
     * error logging message.
     *
     * @param message TODO DOCUMENT ME!
     * @param element TODO DOCUMENT ME!
     */
    private void errorThrows(String message, Element element) {
        processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.ERROR, message, element);
    }

    /**
     * info logging message.
     *
     * @param message TODO DOCUMENT ME!
     * @param element TODO DOCUMENT ME!
     */
    private void infoThrows(String message, Element element) {
        processingEnv.getMessager().printMessage(javax.tools.Diagnostic.Kind.NOTE, message, element);
    }
}
