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

        public static boolean[] cover_parse = new boolean[21];

        public static OmitValue parse(Type valueType, String defaultValueToOmit){

            //REWRITE FOR FLAG SETTING PURPOSES
            if (char.class.equals(valueType)){
                cover_parse[16] = true;
                if (defaultValueToOmit.length() == 1){
                    cover_parse[17] = true;
                }
            }
            if (Character.class.equals(valueType)){
                cover_parse[18] = true;
                if (defaultValueToOmit.length() == 1){
                    cover_parse[19] = true;
                }
            }


            if ("void".equals(defaultValueToOmit)) {
                cover_parse[0] = true;
                return null;
            } else if ("null".equals(defaultValueToOmit)) {
                cover_parse[1] = true;
                return new OmitValue.Null();
            } else if (boolean.class.equals(valueType)) {
                cover_parse[2] = true;
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Boolean.class.equals(valueType)) {
                cover_parse[3] = true;
                Boolean defaultValue = Boolean.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.booleanValue()");
            } else if (int.class.equals(valueType)) {
                cover_parse[4] = true;
                Integer defaultValue = Integer.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Integer.class.equals(valueType)) {
                cover_parse[5] = true;
                Integer defaultValue = Integer.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.intValue()");
            } else if (byte.class.equals(valueType)) {
                cover_parse[6] = true;
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Byte.class.equals(valueType)) {
                cover_parse[7] = true;
                Byte defaultValue = Byte.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.byteValue()");
            } else if (short.class.equals(valueType)) {
                cover_parse[8] = true;
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s");
            } else if (Short.class.equals(valueType)) {
                cover_parse[9] = true;
                Short defaultValue = Short.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + " == %s.shortValue()");
            } else if (long.class.equals(valueType)) {
                cover_parse[10] = true;
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s");
            } else if (Long.class.equals(valueType)) {
                cover_parse[11] = true;
                Long defaultValue = Long.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "L == %s.longValue()");
            } else if (float.class.equals(valueType)) {
                cover_parse[12] = true;
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s");
            } else if (Float.class.equals(valueType)) {
                cover_parse[13] = true;
                Float defaultValue = Float.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "F == %s.floatValue()");
            } else if (double.class.equals(valueType)) {
                cover_parse[14] = true;
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s");
            } else if (Double.class.equals(valueType)) {
                cover_parse[15] = true;
                Double defaultValue = Double.valueOf(defaultValueToOmit);
                return new OmitValue.Parsed(defaultValue, defaultValueToOmit + "D == %s.doubleValue()");
            } else if (char.class.equals(valueType) && defaultValueToOmit.length() == 1) {
                Character defaultValue = defaultValueToOmit.charAt(0);
                return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s");
            } else if (Character.class.equals(valueType) && defaultValueToOmit.length() == 1) {
                Character defaultValue = defaultValueToOmit.charAt(0);
                return new OmitValue.Parsed(defaultValue, "'" + defaultValueToOmit + "' == %s.charValue()");
            } else {
                cover_parse[20] = true;
                throw new UnsupportedOperationException("failed to parse defaultValueToOmit: " + defaultValueToOmit);
            }
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
