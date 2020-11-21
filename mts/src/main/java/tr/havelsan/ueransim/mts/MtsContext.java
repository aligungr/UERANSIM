/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.mts;

public class MtsContext {

    public final TypeRegistry typeRegistry;
    public final MtsConvert converter;
    public final MtsConstruct constructor;
    public final MtsDecoder decoder;

    private String typeKeyword = "@type";
    private boolean kebabCaseDecoding = false;

    public MtsContext() {
        this.typeRegistry = new TypeRegistry();
        this.converter = new MtsConvert(this);
        this.constructor = new MtsConstruct(this);
        this.decoder = new MtsDecoder(this);
    }

    public String getTypeKeyword() {
        return typeKeyword;
    }

    public void setTypeKeyword(String typeKeyword) {
        if (!typeKeyword.startsWith("@"))
            throw new IllegalArgumentException("keyword must start with '@'");
        if (typeKeyword.equals("@ref"))
            throw new IllegalArgumentException("invalid keyword");
        this.typeKeyword = typeKeyword;
    }

    public boolean isKebabCaseDecoding() {
        return kebabCaseDecoding;
    }

    public void setKebabCaseDecoding(boolean kebabCaseDecoding) {
        this.kebabCaseDecoding = kebabCaseDecoding;
    }
}
