package com.runsim.backend.app.codegen;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class BitNGenerator {

    public static void main(String[] args) throws Exception {
        System.out.println("Enter path:");
        var path = new Scanner(System.in).nextLine();
        for (int i = 2; i <= 12; i++) {
            String code = generate(i);
            Files.writeString(Path.of(path, "./Bit" + i + ".java"), code);
        }
    }

    private static String generate(int n) {
        var sb = new StringBuilder();
        sb.append("package com.runsim.backend.utils.bits;\n");
        sb.append("\n");
        sb.append("public final class Bit");
        sb.append(n);
        sb.append(" extends BitN {\n\n");
        sb.append("    public Bit");
        sb.append(n);
        sb.append("(int value) {\n");
        sb.append("        super(value, ");
        sb.append(n);
        sb.append(");\n");
        sb.append("    }\n\n");
        sb.append("    public Bit");
        sb.append(n);
        sb.append("(");
        for (int i = n - 1; i >= 0; i--) {
            sb.append("Bit bit");
            sb.append(i);
            if (i != 0)
                sb.append(", ");
        }
        sb.append(")");
        sb.append(" {\n");
        sb.append("        super(");
        for (int i = n - 1; i >= 0; i--) {
            sb.append("bit");
            sb.append(i);
            if (i != 0)
                sb.append(", ");
        }
        sb.append(");\n");
        sb.append("    }\n\n");
        sb.append("    public Bit");
        sb.append(n);
        sb.append("(");
        for (int i = n - 1; i >= 0; i--) {
            sb.append("int bit");
            sb.append(i);
            if (i != 0)
                sb.append(", ");
        }
        sb.append(")");
        sb.append(" {\n");
        sb.append("        super(");
        for (int i = n - 1; i >= 0; i--) {
            sb.append("bit");
            sb.append(i);
            if (i != 0)
                sb.append(", ");
        }
        sb.append(");\n");
        sb.append("    }\n");
        sb.append("}");
        return sb.toString();
    }
}
