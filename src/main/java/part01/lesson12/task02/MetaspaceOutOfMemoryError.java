package part01.lesson12.task02;

import javassist.CannotCompileException;
import javassist.ClassPool;

/**
 * -XX:MaxMetaspaceSize=512m
 * return OutOfMemoryError Metaspace
 *
 * @author Oleg_Chapurin
 */
public class MetaspaceOutOfMemoryError {

    /**
     * -XX:MaxMetaspaceSize=512m
     * return OutOfMemoryError Metaspace
     */

    public static void main(String[] args) {
        ClassPool cp = ClassPool.getDefault();
        for (int i = 0; i < 100000000; i++){
            try {
                Class c = cp.makeClass("part01.lesson12.task02.ReadWriteConsole" + i).toClass();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            }
        }
    }
}
