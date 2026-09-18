package de.tudresden.multiagentsystem.annotationprocessor;

public class TemplateEscaper {
    public String methodName(String in) {
        if (in == null) return "";
        return in.replaceAll("[^A-Za-z0-9_]", "_");
    }

    public String actionName(String in) {
        if (in == null) return "";
        return in.replaceAll("[^A-Za-z0-9_]", "_");
    }

    public String instanceName(String contractName) {
        if (contractName == null || contractName.isEmpty()) return "inst";
        String s = contractName.substring(0,1).toLowerCase() + contractName.substring(1);
        return s;
    }
}
