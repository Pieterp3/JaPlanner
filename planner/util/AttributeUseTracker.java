package util;


import util.io.Save;
import util.structures.List;
import util.structures.Map;

public class AttributeUseTracker {

    private static Map<String, List<AttributeStat>> data = new Map<>();
    private static Map<String, String> debuggingAttributes = new Map<>();
    private static boolean saveData = false;
    private static AttributeUseTracker instance = new AttributeUseTracker();
    private static final String debugComponent = "ComponentList";

    public static void init() {
        saveData = true;
    }

    public static void updateStat(String attribute, String value) {
        List<AttributeStat> stats = getAttributeStats(attribute);
        String user = getCaller(attribute);
        for (AttributeStat stat : stats) {
            if (stat.getUser().equals(user)) {
                stat.addValue(value);
                return;
            }
        }
        stats.add(instance.new AttributeStat(user, value));
    }

    public static void setFetchedByUser(String attribute) {
        List<AttributeStat> stats = getAttributeStats(attribute);
        String user = getCaller(attribute);
        for (AttributeStat stat : stats) {
            if (stat.getUser().equals(user)) {
                stat.setFetchedByUser(true);
                return;
            }
        }
    }

    private static String getCaller(String attribute) {
        StackTraceElement[] trace = Thread.currentThread().getStackTrace();
        String user = "User";
        for (int i = trace.length-1;i>-1; i--) {
            String cn1 = trace[i].getClassName();
            if (cn1.contains("style")) continue;
            if (cn1.contains("ui.components.impl")) {
                user = cn1;
            }
        }
        user = getClassName(user);
        if (user.equalsIgnoreCase(debugComponent)) {
            debuggingAttributes.put(attribute, user);
        }
        return getClassName(user);
    }

    private static String getClassName(String s) {
        if(s.contains(" ")) {
            return s.substring(s.lastIndexOf('.') + 1, s.indexOf(" "));
        }
        return s.substring(s.lastIndexOf('.') + 1);
    }

    private static List<AttributeStat> getAttributeStats(String attribute) {
        if (!data.containsKey(attribute)) {
            data.put(attribute, new List<>());
        }
        List<AttributeStat> stats = data.get(attribute);
        return stats;
    }

    public static void save() {
        if (!saveData) return;
        List<String> lines = new List<>();
        List<String> keys = data.getKeys();
        if (debugComponent != null) {
            lines.add("-----------------------------------------------");
            lines.add("| Debug Mode enabled for component");
            lines.add("| Debugging " + debugComponent);
            lines.add("| Only information about this component will be displayed");
            lines.add("-----------------------------------------------");
            lines.add("");
            keys = debuggingAttributes.getKeys();
        }
        for (String attribute : keys) {
            List<AttributeStat> stats = data.get(attribute);
            String totalUsers = "\tTotal Users: " + stats.size();
            if (stats.size() == 0) continue;
            if (displayStatUsers(stats).equals("None")) continue;
            lines.add("New Declared Attribute: " + attribute);
            lines.add(totalUsers);
            lines.add("\t\t"+displayStatUsers(stats));
            for (AttributeStat stat : stats) {
                if (debugComponent != null && !stat.getUser().equals(debugComponent)) {
                    continue;
                }
                lines.add("\tValues for " + attribute + " set by " + stat.getUser());
                lines.add("\t\t" + displayStatValues(stat));
                if (!stat.isFetchedByUser()) {
                    lines.add("\t\t\t - Set, but not used, by "+stat.getUser()+" -");
                }
            }
            lines.add("----------------------------------------------\t");
        }
        Save.saveDataFile("AttributeUse", lines);
    }

    private static String displayStatValues(AttributeStat stat) {
        String output = "[";
        for (String value : stat.getValues()) {
            output += value + ", ";
        }
        return output.substring(0, output.length() - 2) + "]";
    }

    private static String displayStatUsers(List<AttributeStat> stats) {
        String output = "[";
        for (AttributeStat stat : stats) {
            if (debugComponent != null && !stat.getUser().equals(debugComponent)) {
                continue;
            }
            output += stat.getUser() + " " + stat.getCount() + ", ";
        }
        if (output.length() < 2) {
            return "None";
        }
        return output.substring(0, output.length() - 2) + "]";
    }

    private class AttributeStat implements Comparable<AttributeStat> {
        private String user;
        private List<String> values;
        private boolean fetchedByUser;

        public AttributeStat(String user, String value) {
            this.user = user;
            values = new List<>();
            values.add(value);
        }

        public boolean isFetchedByUser() {
            return fetchedByUser;
        }

        public void setFetchedByUser(boolean fetchedByUser) {
            this.fetchedByUser = fetchedByUser;
        }

        public String getUser() {
            return user;
        }

        public void addValue(String value) {
            if (!values.contains(value))
            values.add(value);
        }

        public List<String> getValues() {
            return values;
        }

        public int getCount() {
            return values.size();
        }

        @Override
        public int compareTo(AttributeStat o) {
            if (user.equals(o.getUser())) {
                return values.compareTo(o.getValues());
            }
            return user.compareTo(o.getUser());
        }
    }

}
