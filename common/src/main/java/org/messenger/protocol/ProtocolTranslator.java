package org.messenger.protocol;
public class ProtocolTranslator {
    /**
     * Converts instruction into a valid string that can be converted back into a {@link Instruction};
     *
     * @param instruction that needs to be converted
     * @return converted string
     */
    public static String encode(Instruction instruction) {
        StringBuilder msg = new StringBuilder(instruction.getName());

        msg.append("{");

        if (instruction.getParamList() != null) {
            for (String key : instruction.getParamList().keySet()) {
                msg.append(key).append("=").append(instruction.getParamList().get(key)).append(";");
            }
        }

        msg.append("}");

//        if(!checkValidity(msg.toString())){
//            I don't know what to do here, but it should not happen, so I won't do anything
//        }

        return msg.toString();
    }

    public static Instruction decode(String message) throws InvalidStringException {
        if (!checkValidity(message)) {
            throw new InvalidStringException("This is not a valid protocol message");
        }

        // If the message contains only the name of the instruction
        if (!message.contains("{")) {
            return new Instruction(message);
        }

        int bracketIndex = message.indexOf('{');

        String name = message.substring(0, bracketIndex);
        ParamList paramList = getParamList(message, bracketIndex);

        return new Instruction(name, paramList);
    }

    /**
     * Return list of params that are present in the text of an instruction.
     * The IDE entirely made this method.
     * <br>
     * <br>
     * I don't even know why it needs to exist.
     * <br>
     * At least it doesn't give me a warning anymore.
     *
     * @param message      instruction in the text form
     * @param bracketIndex index of '{' in the instruction
     * @return list of parameters found in the text instruction
     * @throws InvalidStringException if one of the parameters contains only a key or a value.
     */
    // I am trying to get code with no warnings,
    // and this class gives me a warning that ParamList is a map not a list in the JavaDocs
    @SuppressWarnings("MismatchedJavadocCode")
    private static ParamList getParamList(String message, int bracketIndex) throws InvalidStringException {
        ParamList paramList = new ParamList();

        if (message.contains(";")) {
            String params = message.substring(bracketIndex + 1, message.length() - 1);

            String[] choppedParams = params.split(";");

            try {
                for (String param : choppedParams) {
                    String[] kvp = param.split("=");
                    paramList.put(kvp[0], kvp[1]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // This code happens when one parameter doesn't have a value or a key
                throw new InvalidStringException("Invalid instruction");
            }

        }

        return paramList;
    }

    /**
     * Checks it the string is valid and can be converted into an instance of {@link Instruction}.
     *
     * @param message that needs to be checked
     * @return true if the string is valid as an instruction, returns false if the string isn't valid
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean checkValidity(String message) {
        return message.matches("\\w+([{](.+=.+;)*[}])?");
    }
}
