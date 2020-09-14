class Asm {
    private Ope Ope;  
    private int reg ;
    private int addr;
    
    
    Asm (Ope opcode, int flag, int address) {
        Ope = opcode;
        reg = flag;
        addr = address;
    }

   
    Asm (String opcode, int flag, int address) {
        Ope = str2Opeartor (opcode);
        reg = flag;
        addr = address;
    }

    
    String printAsm() {
        
        String op_oprndCodeList = 
            "PUSH PUSHI POP POPI BLT BLE IF BNE BGE BGT JUMP";
            
        switch (Ope) {
            case PUSH:
            case PUSHI:
            case POP:
            case IF:
            case JUMP:
                return  Ope.name() + " " + addr;
            default:
                return  Ope.name();
        }
    }


    Ope getOpe() {
        return Ope;
    }
    

    int getAddr() {
        return addr;
    }


    int getReg() {
        return reg;
    }


    boolean equals (Ope opcode) {
        return (Ope.equals (opcode));
    }


    boolean equals (String op) {
        Ope opcode = str2Opeartor(op);
        return (Ope.equals (opcode));
    }


    Ope str2Opeartor (String op) {
        if (op.equals ("ASSGN"))    return Ope.ASSGN;
        else if (op.equals ("ADD"))      return Ope.ADD;
        else if (op.equals ("SUB"))      return Ope.SUB;
        else if (op.equals ("MUL"))      return Ope.MUL;
        else if (op.equals ("DIV"))      return Ope.DIV;
        else if (op.equals ("PUSH"))     return Ope.PUSH;
        else if (op.equals ("PUSHI"))    return Ope.PUSHI;
        else if (op.equals ("REMOVE"))   return Ope.REMOVE;
        else if (op.equals ("POP"))      return Ope.POP;
        else if (op.equals ("INC"))      return Ope.INC;
        else if (op.equals ("DEC"))      return Ope.DEC;
        else if (op.equals ("JUMP"))     return Ope.JUMP;
        else if (op.equals ("IF"))      return Ope.IF;
        else if (op.equals ("HALT"))     return Ope.HALT;
        else if (op.equals ("INPUT"))    return Ope.INPUT;
        else if (op.equals ("OUTPUT"))   return Ope.OUTPUT;
        else                             return Ope.ERR;
    }
}
