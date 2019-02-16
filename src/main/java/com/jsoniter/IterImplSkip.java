package com.jsoniter;

import java.io.IOException;

class IterImplSkip {

    static final boolean[] breaks = new boolean[127];

    // boolean array for computing the branch coverage
    public static boolean[] cover_skip = new boolean[18];

    static {
        breaks[' '] = true;
        breaks['\t'] = true;
        breaks['\n'] = true;
        breaks['\r'] = true;
        breaks[','] = true;
        breaks['}'] = true;
        breaks[']'] = true;
    }

    public static final void skip(JsonIterator iter) throws IOException {
        byte c = IterImpl.nextToken(iter);
        switch (c) {
            case '"':
                cover_skip[0] = true;
                IterImpl.skipString(iter);
                return;
            case '-':
                cover_skip[1] = true;
            case '0':
                cover_skip[2] = (c == '0');
            case '1':
                cover_skip[3] = (c == '1');
            case '2':
                cover_skip[4] = (c == '2');
            case '3':
                cover_skip[5] = (c == '3');
            case '4':
                cover_skip[6] = (c == '4');
            case '5':
                cover_skip[7] = (c == '5');
            case '6':
                cover_skip[8] = (c == '6');
            case '7':
                cover_skip[9] = (c == '7');
            case '8':
                cover_skip[10] = (c == '8');
            case '9':
                cover_skip[11] = (c == '9');
                IterImpl.skipUntilBreak(iter);
                return;
            case 't':
                cover_skip[12] = true;
            case 'n':
                cover_skip[13] = (c == 'n');
                IterImpl.skipFixedBytes(iter, 3); // true or null
                return;
            case 'f':
                cover_skip[14] = true;
                IterImpl.skipFixedBytes(iter, 4); // false
                return;
            case '[':
                cover_skip[15] = true;
                IterImpl.skipArray(iter);
                return;
            case '{':
                cover_skip[16] = true;
                IterImpl.skipObject(iter);
                return;
            default:
                cover_skip[17] = true;
                throw iter.reportError("IterImplSkip", "do not know how to skip: " + c);
        }
    }

    // adapted from: https://github.com/buger/jsonparser/blob/master/parser.go
    // Tries to find the end of string
    // Support if string contains escaped quote symbols.
    final static int findStringEnd(JsonIterator iter) {
        boolean escaped = false;
        for (int i = iter.head; i < iter.tail; i++) {
            byte c = iter.buf[i];
            if (c == '"') {
                if (!escaped) {
                    return i + 1;
                } else {
                    int j = i - 1;
                    for (; ; ) {
                        if (j < iter.head || iter.buf[j] != '\\') {
                            // even number of backslashes
                            // either end of buffer, or " found
                            return i + 1;
                        }
                        j--;
                        if (j < iter.head || iter.buf[j] != '\\') {
                            // odd number of backslashes
                            // it is \" or \\\"
                            break;
                        }
                        j--;
                    }
                }
            } else if (c == '\\') {
                escaped = true;
            }
        }
        return -1;
    }
}
