package util;

import structures.Map;
import util.io.FileIO;
import util.io.Save;
import structures.List;

public class AttributeUseTracker {
    
    private static Map<String, List<AttributeStat>> data = new Map<>();
    private static boolean saveData = false;
    private static AttributeUseTracker instance = new AttributeUseTracker();

    public static void init() {
        saveData = true;
    }

    public static void updateStat(String attribute, String value, String defaultValue) {
        if (!data.containsKey(attribute)) {
            data.put(attribute, new List<>());
        }
        List<AttributeStat> stats = data.get(attribute);
        String user = Thread.currentThread().getStackTrace()[3].getClassName();
        user= user.substring(user.lastIndexOf('.')+1);
        for (AttributeStat stat : stats) {
            if (stat.getUser().equals(user) && stat.getValue().equals(value)) {
                stat.increment();
                return;
            }
        }
        stats.add(instance.new AttributeStat(user, value, defaultValue));
    }

    public static void save() {
        if (!saveData) {
            return;
        }
        List<String> lines = new List<>();
        for (String attribute : data.keySet()) {
            List<AttributeStat> stats = data.get(attribute);
            
            List<String> comps = new List<>();
            for (AttributeStat stat : stats) {
                if (!comps.contains(stat.getUser())) {
                    comps.add(stat.getUser());
                }
            }
            String compsUsed = "\tComponents that use this attribute: [";
            comps.sort();
            for (String comp : comps) {
                compsUsed += comp + ", ";
            }
            lines.add("New Attribute: '"+attribute+"'\n\tUsed by " + comps.size() + " components");
            lines.add("\tDefault Value: " + stats.get(0).getValue());
            lines.add(compsUsed.substring(0, compsUsed.length()-2) + "]\n");
            lines.add("\tValues used: ");
            for (AttributeStat stat : stats) {
                if (!stat.isDefault()) {
                    lines.add("\t\t" + stat.getValue() + " (" + stat.getCount() + " times)");
                }
            }
            lines.add("---------------------------------\n\n");
        }
        Save.saveDataFile("AttributeUse", lines);
    }

    private class AttributeStat implements Comparable<AttributeStat> {
        private String user;
        private String value;
        private String defaultValue;
        private int count;

        public AttributeStat(String user, String value, String defaultValue) {
            this.user = user;
            this.value = value;
            this.defaultValue = defaultValue;
            count = 1;
        }

        public void increment() {
            count++;
        }

        public boolean isDefault() {
            return value.equals(defaultValue);
        }

        public String getUser() {
            return user;
        }

        public String getValue() {
            return value;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(AttributeStat o) {
            if (user.equals(o.getUser())) {
                return value.compareTo(o.getValue());
            }
            return user.compareTo(o.getUser());
        }
    }

}
