package com.ningso.deviceinfo.entitys;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface ICodeable {

    void decode(DataInputStream dataInputStream) throws IOException;

    void encode(DataOutputStream dataOutputStream) throws IOException;
}
