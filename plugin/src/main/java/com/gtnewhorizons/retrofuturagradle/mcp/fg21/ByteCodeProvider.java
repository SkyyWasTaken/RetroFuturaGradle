package com.gtnewhorizons.retrofuturagradle.mcp.fg21;

import com.gtnewhorizons.retrofuturagradle.fg21shadow.org.jetbrains.java.decompiler.main.extern.IBytecodeProvider;
import com.gtnewhorizons.retrofuturagradle.fg21shadow.org.jetbrains.java.decompiler.util.InterpreterUtil;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ByteCodeProvider implements IBytecodeProvider {
    @Override
    public byte[] getBytecode(String externalPath, String internalPath) throws IOException {
        File file = new File(externalPath);
        if (internalPath == null) {
            return InterpreterUtil.getBytes(file);
        } else {
            try (ZipFile archive = new ZipFile(file)) {
                ZipEntry entry = archive.getEntry(internalPath);
                if (entry == null) {
                    throw new IOException("Entry not found: " + internalPath);
                }
                return InterpreterUtil.getBytes(archive, entry);
            }
        }
    }
}