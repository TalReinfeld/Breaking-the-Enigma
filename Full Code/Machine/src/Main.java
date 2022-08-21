//import parts.PlugBoard;
//import parts.PlugBoardImp;
//import parts.ReflectorImp;
//import parts.RotorImp;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        String input = "AAAEEEBBBDDDCCCFFF";
//
//        String abcString = "ABCDEF";
//
//        List<String> abc = new ArrayList<>();
//        abc.add("A");
//        abc.add("B");
//        abc.add("C");
//        abc.add("D");
//        abc.add("E");
//        abc.add("F");
//
//        PlugBoardImp plugBoard = new PlugBoardImp(abcString);
//        plugBoard.setPlug("A", "F");
//
//        List<RotorImp> rotors = new ArrayList<>();
//        List<String> abcLeft1 = new ArrayList<>();
//        abcLeft1.add("F");
//        abcLeft1.add("E");
//        abcLeft1.add("D");
//        abcLeft1.add("C");
//        abcLeft1.add("B");
//        abcLeft1.add("A");
//        rotors.add(new RotorImp(abcLeft1, abc,1, 3));
//        rotors.get(0).setPosition(2);
//        List<String> abcLeft2 = new ArrayList<>();
//        abcLeft2.add("E");
//        abcLeft2.add("B");
//        abcLeft2.add("D");
//        abcLeft2.add("F");
//        abcLeft2.add("C");
//        abcLeft2.add("A");
//        rotors.add(new RotorImp(abcLeft2, abc,2, 0));
//        rotors.get(1).setPosition(2);
//
//        ReflectorImp reflector = new ReflectorImp(abc.size());
//        reflector.setId("I");
//        reflector.swap(0, 3);
//        reflector.swap(1, 4);
//        reflector.swap(2, 5);
//
//        MachineImp machineImp = new MachineImp(abc.toString(), plugBoard, rotors, reflector);
//        for (int i = 0; i < input.length(); i++) {
//            System.out.println(machineImp.encrypt(input.substring(i, i + 1)));
//        }
//    }
//}
