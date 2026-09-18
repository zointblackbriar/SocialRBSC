/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.annotationprocessor;

import java.util.logging.Logger;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.SimpleElementVisitor8;


/**
 * A custom visitor class for processing various elements in the Java Compiler API. This class is used during annotation
 * processing to visit and log information about specific elements like methods, packages, types, and variables.
 *
 * @author Orcun Oruc
 */
public class CustomElementVisitor extends SimpleElementVisitor8<Void, Void> {

    //~ Static fields/initializers -----------------------------------------------------------------------------------------------

    /**
     * Logger definition of the Visitor pattern.
     */
    private static final Logger logger = Logger.getLogger(CustomElementVisitor.class.getName());

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * Visits executable elements (e.g., methods or constructors).
     *
     * @param  e the executable element to visit
     * @param  p an optional parameter for visitor context (not used here)
     *
     * @return always returns {@code null}
     */
    @Override
    public Void visitExecutable(ExecutableElement e, Void p) {
        logger.info("visiting method " + e.getKind());

        return super.visitExecutable(e, p);
    }

    /**
     * Visits package elements.
     *
     * @param  pe the package element to visit
     * @param  p  an optional parameter for visitor context (not used here)
     *
     * @return always returns {@code null}
     */
    @Override
    public Void visitPackage(PackageElement pe, Void p) {
        // Process package elements here
        logger.info("Visited package: " + pe.getSimpleName());

        return super.visitPackage(pe, p);
    }

    /**
     * Visits type elements (e.g., classes, interfaces, or enums).
     *
     * @param  e the type element to visit
     * @param  p an optional parameter for visitor context (not used here)
     *
     * @return always returns {@code null}
     */
    @Override
    public Void visitType(TypeElement e, Void p) {
        // Process type elements here
        logger.info("Visited type: " + e.getSimpleName());

        return super.visitType(e, p);
    }

    /**
     * Visits variable elements (e.g., fields or local variables).
     *
     * @param  ve the variable element to visit
     * @param  p  an optional parameter for visitor context (not used here)
     *
     * @return always returns {@code null}
     */
    @Override
    public Void visitVariable(VariableElement ve, Void p) {
        // Process variable element here
        logger.info("Visited variable: " + ve.getSimpleName());

        return super.visitVariable(ve, p);
    }
}
