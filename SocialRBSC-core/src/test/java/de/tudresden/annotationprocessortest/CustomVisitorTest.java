/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.annotationprocessortest;

import java.util.logging.Logger;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.tudresden.multiagentsystem.annotationprocessor.CustomElementVisitor;


/**
 * TODO DOCUMENT ME!
 *
 * @author $author$
 */
public class CustomVisitorTest {

    //~ Instance fields ----------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    private Logger mockLogger;

    /**
     * TODO DOCUMENT ME!
     */
    private CustomElementVisitor visitor;

    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /**
     * TODO DOCUMENT ME!
     */
    @BeforeEach
    public void setUp() {
        visitor = new CustomElementVisitor();
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testVisitExecutable() {
        // Mock an executable element
        ExecutableElement mockExecutable = mock(ExecutableElement.class);

        Name mockName = mock(Name.class);

        // Mock a name object to represent the method name
        when(mockName.toString()).thenReturn("testMethod");
        when(mockExecutable.getSimpleName()).thenReturn(mockName);

        try {
        } catch (Exception e) {
            fail("visitExecutable should not throw an exception:" + e.getMessage());
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testVisitType() {
        // Mock a type element(e.g. class or interface)
        TypeElement mockType = mock(TypeElement.class);
        String returnedMockElement = "com.example.MyClass";
        when(mockType.toString()).thenReturn(returnedMockElement);

        try {
            visitor.visitType(mockType, null);
        } catch (Exception e) {
            fail("visitType should not throw an exception: " + e.getMessage());
        }
    }

    /**
     * TODO DOCUMENT ME!
     */
    @Test
    public void testVisitVariable() {
        VariableElement mockVariable = mock(VariableElement.class);
        System.out.println("mockVariable: " + mockVariable);
        when(mockVariable.toString()).thenReturn("testVariable");

        try {
            visitor.visitVariable(mockVariable, null);
        } catch (Exception e) {
            fail("VisitVariable should not throw an exception: " + e.getMessage());
        }
    }
}
