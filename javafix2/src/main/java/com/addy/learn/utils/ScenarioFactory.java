package com.addy.learn.utils;

import java.util.Map;

public class ScenarioFactory {

    public static class InvalidScenarioException extends Exception{

    }



    private Map<String,String> classMap;

    public ScenarioFactory(){
        classMap.put("Stuck","com.addy.learn.stuck.ManIamStuck");
        classMap.put("Hogger","com.addy.learn.stuck.Hogger");
    }

    private Class<Scenario> getScenario(String classKey) throws InvalidScenarioException {

            if (!classMap.containsKey(classKey)){
                throw new InvalidScenarioException();
            }

            String classPath =  classMap.get(classKey);
        try {
            Class cls = Class.forName(classPath);
            return cls;
        } catch (ClassNotFoundException e) {
            throw new InvalidScenarioException();
        }
    }

    public Scenario getInstance(String scenarioKey){
        try {
            Scenario s = (Scenario) getScenario(scenarioKey).newInstance();
            return s;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvalidScenarioException e) {
            e.printStackTrace();
        }
        // shoud never reach here
        return null;
    }

}
