package com.mayab.quality.unittest.unittest;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mayab.quality.unittest.doubles.Dependency;
import com.mayab.quality.unittest.doubles.SubDependency;

import static org.hamcrest.MatcherAssert.assertThat;

class DependencyTest {
    
    private Dependency dependency;
    private SubDependency subdependency;

    @BeforeEach
    void setup() {
       
        subdependency = mock(SubDependency.class);
        when(subdependency.getClassName()).thenReturn("Mockito subdependency");
        dependency = new Dependency(subdependency);
    }

    @Test
    void getClassNameTest() {
    
        String name = dependency.getSubDependencyClassName();
        System.out.println("Name: " + name);
        assertThat(name, is("Mockito subdependency"));
    }
}
