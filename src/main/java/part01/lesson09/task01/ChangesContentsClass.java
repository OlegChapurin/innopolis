package part01.lesson09.task01;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Changes the contents of a class
 *
 * @author Oleg_Chapurin
 */
public class ChangesContentsClass {

    private StringBuffer stringBufferConsole = new StringBuffer();
    private StringBuffer stringBufferFile = new StringBuffer();
    private String fileDestination;
    private String fileSource;

    /**
     *
     * @param destination path class template
     * @param source path class compiler
     */
    public ChangesContentsClass(String destination, String source) {
        this.fileDestination = destination;
        this.fileSource = source;
    }

    /**
     *
     * @param fileDestinationSource path class template and class compiler
     */
    public ChangesContentsClass(String fileDestinationSource) {
        this.fileDestination = fileDestinationSource;
        this.fileSource = fileDestinationSource;
    }

    /**
     *
     * @param insert insertion point in class
     */
    public void replaceContents(String insert){
        ReadWrite.newFlowReadConsole();
        ReadWrite.newFlowWriteConsole();
        ReadWrite.writeConsole("Add text method doWork");
        String message;
        while(true){
            message = ReadWrite.readConsoleString();
            if(message.length() == 0){
                break;
            }
            stringBufferConsole.append(message + "\n");
        }
        ReadWrite.newFlowReadFile(fileDestination);
        while((message = ReadWrite.readLineFile()) != null){
            if(insert.equals(message.trim())){
                stringBufferFile.append(stringBufferConsole.substring(0) + "\n");
            }else {
                stringBufferFile.append(message+ "\n");
            }
        }
        ReadWrite.closeReaderFlowFile();
        File file = new File(fileSource);
        file.delete();
        ReadWrite.newFlowWriteFile(fileSource);
        ReadWrite.writeFile(stringBufferFile.substring(0));
        ReadWrite.closeWriterFlowFile();
        ReadWrite.closeAllFlowConsole();
        /** Compiler */
        MyCompiler myCompiler = new MyCompiler();
        myCompiler.setListFiles(fileSource);
        myCompiler.compiler();
        ClassLoader l = new MyClassLoader("part01.lesson09.task01.SomeClass",fileSource);
        Class<?> someClass = null;
        try {
            someClass = l.loadClass("part01.lesson09.task01.SomeClass");
            try {
                Method method = someClass.getDeclaredMethod("doWork");
                try {
                    method.invoke(someClass.newInstance());
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileDestination = "src\\main\\java\\part01\\lesson09\\task01\\SomeClass.java";
//        String fileSource = "target\\classes\\part01\\lesson09\\task01\\SomeClass.java";
////        String fileDestination = System.getProperty("user.home") + "\\part01\\lesson09\\task01\\SomeClass.java";
////        String fileSource = System.getProperty("user.home") + "\\part01\\lesson09\\task01\\SomeClass.java";
//        ChangesContentsClass changesContentsClass = new ChangesContentsClass(fileDestination,fileSource);
//        changesContentsClass.replaceContents("//##//");
    }
}
