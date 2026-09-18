/*
 * Copyright 2022-2024 Orcun Oruc
 *
 * You should have received a copy of a license with this program. 
 *
 * You may not use, copy, modify, sublicense, or distribute the Program or any
 * portion of it, except as expressly provided under the given license.
 */
package de.tudresden.multiagentsystem.annotationprocessor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import de.tudresden.codegenerator.soliditygenerator.SolidityGenerator;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Compartment;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.NaturalType;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.Role;
import de.tudresden.socialrbscdeterministic.rolecompartment.annotation.RoleMethod;

/**
 * Test class for RoleProcessor annotation processor.
 *
 * @author Orcun Oruc
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RoleProcessorTest {

    @Mock
    private Logger mockLogger;

    @Mock
    private SolidityGenerator mockSolidityGenerator;

    @Mock
    private ProcessingEnvironment mockProcessingEnv;

    @Mock
    private Messager mockMessager;

    @Mock
    private RoundEnvironment mockRoundEnv;

    @Mock
    private TypeElement mockTypeElement;

    @Mock
    private Element mockRoleElement;

    @Mock
    private Element mockCompartmentElement;

    @Mock
    private Element mockRoleMethodElement;

    @Mock
    private Element mockNaturalTypeElement;

    @Mock
    private Name mockName;

    private RoleProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new RoleProcessor();
        processor.init(mockProcessingEnv);
        when(mockProcessingEnv.getMessager()).thenReturn(mockMessager);
    }

    @Test
    void testProcessWithEmptyAnnotations() {
        // Given
        Set<TypeElement> emptyAnnotations = Collections.emptySet();

        // When
        boolean result = processor.process(emptyAnnotations, mockRoundEnv);

        // Then
        assertFalse(result, "Should return false when no annotations are present");
    }

    @Test
    void testProcessWithRoleAnnotation() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> roleElements = new HashSet<>();
        roleElements.add(mockRoleElement);

        Role mockRoleAnnotation = mock(Role.class);

        doReturn(roleElements).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(RoleMethod.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(Compartment.class);

        when(mockRoleElement.getAnnotation(Role.class)).thenReturn(mockRoleAnnotation);
        when(mockRoleElement.getKind()).thenReturn(ElementKind.CLASS);
        when(mockRoleElement.getSimpleName()).thenReturn(mockName);
        when(mockName.toString()).thenReturn("TestRole");
        when(mockRoleElement.getEnclosedElements()).thenReturn(Collections.emptyList());

        // When
        try (MockedStatic<SolidityGenerator> mockedStatic = mockStatic(SolidityGenerator.class)) {
            boolean result = processor.process(annotations, mockRoundEnv);

            // Then
            assertTrue(result, "Should return true when processing is successful");
            mockedStatic.verify(() -> 
                SolidityGenerator.solidityForSocialRbSCRoleGenerateOnlyRole("TestRole"),
                times(1)
            );
        }
    }

    @Test
    void testProcessWithRoleMethodAnnotation() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> roleElements = new HashSet<>();
        roleElements.add(mockRoleElement);

        Set<Element> roleMethodElements = new HashSet<>();
        roleMethodElements.add(mockRoleMethodElement);

        Role mockRoleAnnotation = mock(Role.class);
        RoleMethod mockRoleMethodAnnotation = mock(RoleMethod.class);

        doReturn(roleElements).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);
        doReturn(roleMethodElements).when(mockRoundEnv).getElementsAnnotatedWith(RoleMethod.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(Compartment.class);

        when(mockRoleElement.getAnnotation(Role.class)).thenReturn(mockRoleAnnotation);
        when(mockRoleElement.getKind()).thenReturn(ElementKind.CLASS);
        when(mockRoleElement.getSimpleName()).thenReturn(mockName);
        when(mockName.toString()).thenReturn("TestRole");
        when(mockRoleElement.getEnclosedElements()).thenReturn(Collections.emptyList());

        when(mockRoleMethodElement.getAnnotation(RoleMethod.class)).thenReturn(mockRoleMethodAnnotation);
        when(mockRoleMethodElement.getKind()).thenReturn(ElementKind.METHOD);
        when(mockRoleMethodElement.getSimpleName()).thenReturn(mockName);

        Name mockMethodName = mock(Name.class);
        when(mockRoleMethodElement.getSimpleName()).thenReturn(mockMethodName);
        when(mockMethodName.toString()).thenReturn("testMethod");

        // When
        try (MockedStatic<SolidityGenerator> mockedStatic = mockStatic(SolidityGenerator.class)) {
            boolean result = processor.process(annotations, mockRoundEnv);

            // Then
            assertTrue(result, "Should return true when processing is successful");
            mockedStatic.verify(() -> 
                SolidityGenerator.solidityForRoleMethod("testMethod", "testMethod", "TestRole"),
                times(1)
            );
        }
    }

    @Test
    void testProcessWithCompartmentAnnotation() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> compartmentElements = new HashSet<>();
        compartmentElements.add(mockCompartmentElement);

        Compartment mockCompartmentAnnotation = mock(Compartment.class);

        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(RoleMethod.class);
        doReturn(compartmentElements).when(mockRoundEnv).getElementsAnnotatedWith(Compartment.class);

        when(mockCompartmentElement.getAnnotation(Compartment.class)).thenReturn(mockCompartmentAnnotation);
        when(mockCompartmentElement.getKind()).thenReturn(ElementKind.INTERFACE);
        when(mockCompartmentElement.getSimpleName()).thenReturn(mockName);
        when(mockName.toString()).thenReturn("TestCompartment");

        // When
        try (MockedStatic<SolidityGenerator> mockedStatic = mockStatic(SolidityGenerator.class)) {
            boolean result = processor.process(annotations, mockRoundEnv);

            // Then
            assertTrue(result, "Should return true when processing is successful");
            mockedStatic.verify(() -> 
                SolidityGenerator.solidityForCompartmentCreation("TestCompartment", "TestCompartment"),
                times(1)
            );
        }
    }

    @Test
    void testProcessWithNaturalTypeAndRoleConflict() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> roleElements = new HashSet<>();
        roleElements.add(mockRoleElement);

        Set<Element> naturalTypeElements = new HashSet<>();
        naturalTypeElements.add(mockRoleElement); // Same element with both annotations

        doReturn(roleElements).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(naturalTypeElements).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);

        // When
        boolean result = processor.process(annotations, mockRoundEnv);

        // Then
        assertFalse(result, "Should return false when NaturalType and Role coexist");
        verify(mockMessager).printMessage(
            eq(Diagnostic.Kind.ERROR),
            eq("@NaturalType and @RoleType cannot coexist on the same class"),
            eq(mockRoleElement)
        );
    }

    @Test
    void testProcessWithRoleHavingMethodElements() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> roleElements = new HashSet<>();
        roleElements.add(mockRoleElement);

        Element mockMethodElement = mock(Element.class);
        Element mockOtherElement = mock(Element.class);
        Name mockMethodName = mock(Name.class);

        Role mockRoleAnnotation = mock(Role.class);

        doReturn(roleElements).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(RoleMethod.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(Compartment.class);

        when(mockRoleElement.getAnnotation(Role.class)).thenReturn(mockRoleAnnotation);
        when(mockRoleElement.getKind()).thenReturn(ElementKind.CLASS);
        when(mockRoleElement.getSimpleName()).thenReturn(mockName);
        when(mockName.toString()).thenReturn("TestRole");
        
        when(mockMethodElement.getKind()).thenReturn(ElementKind.METHOD);
        when(mockMethodElement.getSimpleName()).thenReturn(mockMethodName);
        when(mockMethodName.toString()).thenReturn("methodName");
        
        when(mockOtherElement.getKind()).thenReturn(ElementKind.OTHER);
        
        List<Element> enclosedElementsList = new ArrayList<>();
        enclosedElementsList.add(mockMethodElement);
        enclosedElementsList.add(mockOtherElement);
        doReturn(enclosedElementsList).when(mockRoleElement).getEnclosedElements();

        // When
        try (MockedStatic<SolidityGenerator> mockedStatic = mockStatic(SolidityGenerator.class)) {
            boolean result = processor.process(annotations, mockRoundEnv);

            // Then
            assertTrue(result, "Should return true when processing is successful");
            mockedStatic.verify(() -> 
                SolidityGenerator.solidityForSocialRbSCRoleGenerateOnlyRole("TestRole"),
                times(1)
            );
        }
    }

    @Test
    void testProcessWithException() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        when(mockRoundEnv.getElementsAnnotatedWith(Role.class))
            .thenThrow(new RuntimeException("Test exception"));

        // When
        boolean result = processor.process(annotations, mockRoundEnv);

        // Then
        assertFalse(result, "Should return false when an exception occurs");
    }

    @Test
    void testProcessWithNonClassRoleElement() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> roleElements = new HashSet<>();
        roleElements.add(mockRoleElement);

        Role mockRoleAnnotation = mock(Role.class);

        doReturn(roleElements).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(RoleMethod.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(Compartment.class);

        when(mockRoleElement.getAnnotation(Role.class)).thenReturn(mockRoleAnnotation);
        when(mockRoleElement.getKind()).thenReturn(ElementKind.INTERFACE); // Not a CLASS
        doReturn(Collections.emptyList()).when(mockRoleElement).getEnclosedElements();

        // When
        try (MockedStatic<SolidityGenerator> mockedStatic = mockStatic(SolidityGenerator.class)) {
            boolean result = processor.process(annotations, mockRoundEnv);

            // Then
            assertTrue(result, "Should return true even if role element is not a class");
            mockedStatic.verify(() -> 
                SolidityGenerator.solidityForSocialRbSCRoleGenerateOnlyRole(anyString()),
                never()
            );
        }
    }

    @Test
    void testProcessWithNonInterfaceCompartmentElement() {
        // Given
        Set<TypeElement> annotations = new HashSet<>();
        annotations.add(mockTypeElement);

        Set<Element> compartmentElements = new HashSet<>();
        compartmentElements.add(mockCompartmentElement);

        Compartment mockCompartmentAnnotation = mock(Compartment.class);

        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(Role.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(NaturalType.class);
        doReturn(Collections.emptySet()).when(mockRoundEnv).getElementsAnnotatedWith(RoleMethod.class);
        doReturn(compartmentElements).when(mockRoundEnv).getElementsAnnotatedWith(Compartment.class);

        when(mockCompartmentElement.getAnnotation(Compartment.class)).thenReturn(mockCompartmentAnnotation);
        when(mockCompartmentElement.getKind()).thenReturn(ElementKind.CLASS); // Not an INTERFACE

        // When
        try (MockedStatic<SolidityGenerator> mockedStatic = mockStatic(SolidityGenerator.class)) {
            boolean result = processor.process(annotations, mockRoundEnv);

            // Then
            assertTrue(result, "Should return true even if compartment element is not an interface");
            mockedStatic.verify(() -> 
                SolidityGenerator.solidityForCompartmentCreation(anyString(), anyString()),
                never()
            );
        }
    }

    @Test
    void testConstructorWithMocks() {
        // Given & When
        RoleProcessor processorWithMocks = new RoleProcessor(mockLogger, mockSolidityGenerator);

        // Then
        assertNotNull(processorWithMocks, "Processor should be created with mock parameters");
    }
}
