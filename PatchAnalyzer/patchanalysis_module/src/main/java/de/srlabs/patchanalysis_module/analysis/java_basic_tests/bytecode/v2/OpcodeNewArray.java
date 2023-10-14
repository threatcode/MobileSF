package de.srlabs.patchanalysis_module.analysis.java_basic_tests.bytecode.v2;

import de.srlabs.patchanalysis_module.analysis.java_basic_tests.Helper;
import de.srlabs.patchanalysis_module.analysis.java_basic_tests.dexparser.DexFile;

public class OpcodeNewArray extends Opcode {

    @Override
    public byte[] hashBinary(byte[] code, DexFile dexFile, DexSignatureContext context) {
        byte[] slice = Helper.getSlice(code, 2, 4);
        int index = Helper.getUnsignedShort(slice);
        byte[] typeDescriptor = dexFile.getTypeDescriptorRaw(index);
        byte[] zeroByte = new byte[1];
        byte[][] byteSlices = {
                Helper.getSlice(code, 0, 2),
                typeDescriptor,
                zeroByte
        };

        return Helper.concatenateBytes(byteSlices);
    }

    @Override
    public String hashText(byte[] code, DexFile dexFile, DexSignatureContext context) {
        byte[] slice = Helper.getSlice(code, 2, 4);
        int index = Helper.getUnsignedShort(slice);
        String type = dexFile.getType(index).toString();

        return String.format("%s: %s", this.name, type);
    }
}