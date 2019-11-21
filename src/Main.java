import java.util.ArrayList;
import java.util.List;

public class Main {
    //число перестановок
    private static int permutations = 0;

    private static void returnPermutations(int currentPosition, List<Integer> integerList) {
        //создаем лист возможных элементов для перестановки с данным(нулевым, первым и т.д.)
        List<Integer> listOfPossibles = new ArrayList<>();
        /* делаем рекурсию до предпоследнего элемента, т.к. для последнего элемента нет перестановок. Мы прили к концу
        и победили (см. *) */
        if (currentPosition < integerList.size() - 1) {
            for (int i = 1; i < integerList.size() - 1; i++) {
                /* начиная с элемента, последующего к предыдущему просматриваем те, с которыми мы можем свапнуть текущий
                и добавляем его в лист */
                if (Validation.isPermutationValid(currentPosition, i, integerList)) {
                    listOfPossibles.add(integerList.get(i));
                }
            }
            /* если есть такие элементы, с которыми можем свапнуть -> свапаем с каждым , увеличиваем текущую позицию на
               единицу и делаем рекурсию с измененный integerList'ом */
            if (listOfPossibles.size() != 0) {
                //для каждого...
                for (int i = 0; i < listOfPossibles.size(); i++) {
                    //...делаем свап...
                    int temp = integerList.get(currentPosition);
                    listOfPossibles.set(i, listOfPossibles.get(currentPosition));
                    listOfPossibles.set(currentPosition, temp);
                    //...и рекурсию
                    returnPermutations(currentPosition + 1, integerList);
                }
                //если нет возможных элементов для свапа - "перескакивавем" на следующий элемент
            } else {
                returnPermutations(currentPosition + 1, integerList);
            }
        } else {
            //(*) дошли до конца -> все предыдущие перестановки по 2 элемента подходят
            permutations++;
        }
        //при выходе из рекурсии получим число всевозможных перестановок
    }

    public static void main(String[] args) {
        returnPermutations(0, Entity.createArray());
        System.out.println(permutations);
    }
}
