package com.jsoniter.spi;

import java.lang.reflect.Type;

public interface OmitValue {

    boolean shouldOmit(Object val);

    String code();

    class Null implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return val == null;
        }

        @Override
        public String code() {
            return "null == %s";
        }
    }

    class ZeroByte implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Byte) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroShort implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Short) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroInt implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Integer) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroLong implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Long) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroFloat implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Float) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroDouble implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return ((Double) val) == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class ZeroChar implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return (Character) val == 0;
        }

        @Override
        public String code() {
            return "0 == %s";
        }
    }

    class False implements OmitValue {

        @Override
        public boolean shouldOmit(Object val) {
            return !((Boolean) val);
        }

        @Override
        public String code() {
            return "false == %s";
        }
    }

    class Parsed implements OmitValue {

        private final Object defaultValue;
        private final String code;

        public Parsed(Object defaultValue, String code) {
            this.defaultValue = defaultValue;
            this.code = code;
        }

        public static OmitValue parse(Type valueType, String defaultValueToOmit) {
            if ("void".equals(defaultValueToOmit)) {
                return null;
            }else if ("null".equals(defaultValueToOmit)) {
                return new OmitValue.Null();
            }

            Parsed booleans = parseBoolean(valueType, defaultValueToOmit);
            Parsed integers = parseInteger(valueType, defaultValueToOmit);
            Parsed bytes = parseByte(valueType, defaultValueToOmit);
            Parsed shorts = parseShort(valueType, defaultValueToOmit);
            Parsed longs = parseLong(valueType, defaultValueToOmit);
            Parsed foats = parseFloat(valueType, defaultValueToOmit);
            Parsed doubles = parseDouble(valueType, defaultValueToOmit);
            Parsed characters = parseCharacter(valueType, defaultValueToOmit);

            if (booleans != null){
                return booleans;
            }else if (integers != null){
                return integers;
            }else if (bytes != null){
                return bytes;
            }else if (shorts != null){
                return shorts;
            }else if (longs != null){
                return longs;
            }else if (foats != null){
                return foats;
            }else if (doubles != null){
                return doubles;
            }else if (characters != null){
                return characters;
            }else {
                throw new UnsupportedOperationException("failed to parse defaultValueToOmit: " + defaultValueToOmit);
            }
        }
        /**
            Catches possible Boolean type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseBoolean(Type valueType, String defaultValueToOmit){
            if (boolean.class.equals(valueType)) {
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Boolean.class.equals(valueType)) {
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.booleanValue()");
            }
            return null;
        }

        /**
            Catches possible Integer type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseInteger(Type valueType, String defaultValueToOmit){
            if (int.class.equals(valueType)) {
               Integer defaultValue = Integer.valueOf(defaultValueToOmit);
               return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
           } else if (Integer.class.equals(valueType)) {
               Integer defaultValue = Integer.valueOf(defaultValueToOmit);
               return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.intValue()");
           }
           return null;
        }

        /**
            Catches possible Byte type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseByte(Type valueType, String defaultValueToOmit){
            if (byte.class.equals(valueType)) {
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Byte.class.equals(valueType)) {
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.byteValue()");
            }
            return null;
        }

        /**
            Catches possible Short type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseShort(Type valueType, String defaultValueToOmit){
            if (short.class.equals(valueType)) {
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Short.class.equals(valueType)) {
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.shortValue()");
            }
            return null;
        }

        /**
            Catches possible Long type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseLong(Type valueType, String defaultValueToOmit){
            if (long.class.equals(valueType)) {
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s");
            } else if (Long.class.equals(valueType)) {
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s.longValue()");
            }
            return null;
        }

        /**
            Catches possible Float type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseFloat(Type valueType, String defaultValueToOmit){
            if (float.class.equals(valueType)) {
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s");
            } else if (Float.class.equals(valueType)) {
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s.floatValue()");
            }
            return null;
        }

        /**
            Catches possible Double type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseDouble(Type valueType, String defaultValueToOmit){
            if (double.class.equals(valueType)) {
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s");
            } else if (Double.class.equals(valueType)) {
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s.doubleValue()");
            }
            return null;
        }

        /**
            Catches possible Character type nodes and returns a correct Parsed instance.
        */
        public static Parsed parseCharacter(Type valueType, String defaultValueToOmit){
            if(defaultValueToOmit.length() == 1){
                if (char.class.equals(valueType)) {
                    Character defaultValue = defaultValueToOmit.charAt(0);
                    return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s");
                } else if (Character.class.equals(valueType)) {
                    Character defaultValue = defaultValueToOmit.charAt(0);
                    return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s.charValue()");
                }
            }
            return null;
        }

        @Override
        public boolean shouldOmit(Object val) {
            return defaultValue.equals(val);
        }

        @Override
        public String code() {
            return code;
        }
    }
}
