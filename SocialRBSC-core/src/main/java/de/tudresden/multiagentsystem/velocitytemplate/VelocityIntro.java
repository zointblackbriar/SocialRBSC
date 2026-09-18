///*
// * Copyright 2022-2023 Orcun Oruc
// *
// * You should have received a copy of a license with this program.
// *
// * You may not use, copy, modify, sublicense, or distribute the Program or any
// * portion of it, except as expressly provided under the given license.
// */
// package de.tudresden.multiagentsystem.velocitytemplate;
//
// import org.apache.velocity.VelocityContext;
// import org.apache.velocity.app.Velocity;
//
// import java.io.StringWriter;
//
// import java.util.Properties;
// import java.util.logging.Logger;
//
//
///**
// * TODO DOCUMENT ME!
// *
// * @author $author$
// */
// public class VelocityIntro {
//
// //~ Static fields/initializers -----------------------------------------------------------------------------------------------
//
// /**
// * TODO DOCUMENT ME!
// */
// private static final Logger logger = Logger.getLogger(VelocityIntro.class.getName());
//
// //~ Methods ------------------------------------------------------------------------------------------------------------------
//
// /**
// * TODO DOCUMENT ME!
// */
// public void customTemplateUsage() {
// // first we init the runtime engine.
//
// Properties p = new Properties();
// p.setProperty("file.resource.loader.path", "/opt/templates");
//
// // we need to init the runtime engine. Defaults are fine
// Velocity.init();
//
// // let's make a Context and put data into it
//
// VelocityContext context = new VelocityContext();
//
// context.put("name", "Velocity");
// context.put("project", "Jakarta");
//
// // render a template
// StringWriter writer = new StringWriter();
//
// Velocity.mergeTemplate("testTemplate.vm", context, writer);
// logger.info("template: " + writer);
//
// // lets make our own string to render
//
// String str = "We are using $project $name to render this.";
// writer = new StringWriter(); // new object has been created
// Velocity.evaluate(context, writer, "mystring", str);
// logger.info("string : " + writer);
// }
//
// /**
// * TODO DOCUMENT ME!
// */
// @SuppressWarnings("deprecation")
// public void initializeVelocityTemplateContext() {
// Velocity.init();
//
// VelocityContext context = new VelocityContext();
// context.put("name", "Velocity");
// context.put("project", "Jakarta");
//
// StringWriter writer = new StringWriter();
// Velocity.mergeTemplate("testtemplate.vm", context, writer);
// System.out.println("template: " + writer);
//
// // lets make our own string to render
//
// String str = "We are using $project $name to render this.";
// writer = new StringWriter();
// Velocity.evaluate(context, writer, "mystring", str);
// System.out.println("string :" + writer);
// }
// }
