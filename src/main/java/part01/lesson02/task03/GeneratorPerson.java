package part01.lesson02.task03;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Transforms Array Strings To Array Person
 *
 * @author Oleg_Chapurin
 */
class GeneratorPerson {

    private ArrayList<String> arrayError = new ArrayList<>();

    /** Define gender */
    private Gender identifyGender(String sex) throws NullPointerException{
        if(sex == null){
            throw new NullPointerException("Gender not determined");
        }
        if ("MALE".equals(sex.trim().toUpperCase())){
            return Gender.MAN;
        }
        return Gender.WOMAN;
    }

    /** Сonvert array strings to array person */
    private ArrayList<Person> convertArrayStringsToArrayPerson(ArrayList<String> arrayList){
        ArrayList<Person> ap = new ArrayList<>();
        Random randomAge = new Random();
        Scanner scanner = null;
        String line;
        for (int i = 0; i < arrayList.size(); i++){
            line = arrayList.get(i);
            scanner = new Scanner(line);
            scanner.useDelimiter(";");
            if(scanner.hasNext()){
                String name;
                name = scanner.next();
                if(scanner.hasNext()){
                    String sex;
                    sex = scanner.next();
                    try{
                        Gender gender = identifyGender(sex);
                        Person person = new Person(randomAge.nextInt(100),gender,name);
                        ap.add(person);
                    }catch(NullPointerException e){
                        System.out.println(name + " -> " + e);
                    }
                }
            }
        }
        return ap;
    }

    /*** Fill an array with randomly selected objects */
    private void fillNewArrayPerson(ArrayList<Person> newArrayPerson,
                                                 ArrayList<Person> oldArrayPerson,
                                                 int numberOf, int lastNumberOf){
        for(;numberOf < lastNumberOf; numberOf++){
            Person person = oldArrayPerson.get(numberOf);
            if(!newArrayPerson.contains(person)) {
                newArrayPerson.add(person);
            }
        }
    }

    /** Random sampling **/
    private ArrayList<Person> chooseRandomPerson(ArrayList<Person> oldArrayPerson){
        ArrayList<Person> newArrayPerson = new MyArrayList<>();
        Random randomAge = new Random();
        int size = oldArrayPerson.size();
        for (int i =0; i < 20; i++) {
            int numberOf = randomAge.nextInt(size);
            int lastNumberOf = numberOf + 1000;
            if ((lastNumberOf) <= size){
                fillNewArrayPerson(newArrayPerson, oldArrayPerson, numberOf, lastNumberOf);
            }
            if ((lastNumberOf) > size){
                numberOf = numberOf - (size - numberOf);
                fillNewArrayPerson(newArrayPerson, oldArrayPerson, numberOf, size);
            }
        }
        return newArrayPerson;
    }

    /** Get array object Person */
    ArrayList<Person> getArrayPerson(ArrayList<String> arrayList) {
        ArrayList<Person> arrayPerson = convertArrayStringsToArrayPerson(arrayList);
        ArrayList<Person> arrayRandomPerson = chooseRandomPerson(arrayPerson);
        return arrayRandomPerson;
    }
}
