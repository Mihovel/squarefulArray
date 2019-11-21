import java.util.List;

class Validation {
    //проверка, является ли число полным квадратом
    private static boolean isSquare(int toCheck) {
        if (toCheck == 0 || toCheck == 1)
            return true;
        for (int i = 0; i < Math.sqrt(toCheck); i++) {
            int x = i * i;
            if (x == toCheck) {
                return true;
            }
        }
        return false;
    }

    //проверка на возможность перестановки данного элемента с каким-нибудь из следующих
    static boolean isPermutationValid(int minIndex, int maxIndex, List<Integer> integerList) {
        //проверяем, делать ли проверку c (minIndex - 1)-ым элементом(catch ArrayOutOfBoundsException)
        if (minIndex != 0) {
            //проверяем, делать ли проверку c (maxIndex + 1)-ым элементом(catch ArrayOutOfBoundsException)
            if (maxIndex != integerList.size() - 1) {
                /* проверка на возможность перемены местами integerList[minIndex] и integerList[maxIndex] при условии,
                 что: /minIndex > 0
                      \maxIndex < integerList.size() - 1; */

                /* integerList[minIndex] поставить на maxIndex и посмотреть, что с соседними к maxIndex */
                if (isSquare(integerList.get(minIndex) + integerList.get(maxIndex + 1))) {
                    if (isSquare(integerList.get(minIndex) + integerList.get(maxIndex - 1))) {
                        /* integerList[maxIndex] поставить на minIndex и посмотреть, что с соседними к minIndex */
                        if (isSquare(integerList.get(maxIndex) + integerList.get(minIndex + 1))) {
                            if (isSquare(integerList.get(maxIndex) + integerList.get(minIndex - 1))) {
                                /* если все условия выполнены - можно переставить integerList[minIndex] и
                                integerList[maxIndex] местами*/
                                return true;
                            }
                            //во всех остальных случаях - нельзя
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
                /* если maxIndex == integerList.size() - 1, то не надо проверять
                integerList.set(maxIndex + 1, integerList.get(minIndex)) */
            } else {
                //проверка, когда maxIndex == integerList.size() - 1, а minIndex > 0
                if (isSquare(integerList.get(minIndex) + integerList.get(maxIndex - 1))) {
                    if (isSquare(integerList.get(maxIndex) + integerList.get(minIndex - 1))) {
                        if (isSquare(integerList.get(maxIndex) + integerList.get(minIndex + 1))) {
                            //если все условия выполнены - можно переставить
                            return true;
                        }
                        //во всех остальных случаях - нельзя
                        return false;
                    }
                    return false;
                }
                return false;
            }
            /* если minIndex == 0, возможны случаи: */
        } else {
            /* 1. maxIndex == integerList.size() - 1 (меняем первый с последним) - легкая проверка */
            if (maxIndex == integerList.size() - 1) {
                if (isSquare(integerList.get(maxIndex) + integerList.get(minIndex + 1))) {
                    if (isSquare(integerList.get(maxIndex - 1) + integerList.get(minIndex))) {
                        //если все условия выполнены - можно переставить
                        return true;
                    }
                    //во всех остальных случаях - нельзя
                    return false;
                }
                return false;
                /* 2. maxIndex < integerList.size() - 1 (меняем первый с НЕпоследним) */
            } else {
                if (isSquare(integerList.get(minIndex) + integerList.get(maxIndex + 1))) {
                    if (isSquare(integerList.get(minIndex) + integerList.get(maxIndex - 1))) {
                        if (isSquare(integerList.get(maxIndex) + integerList.get(minIndex + 1))) {
                            //если все условия выполнены - можно переставить
                            return true;
                        }
                        //во всех остальных случаях - нельзя
                        return false;
                    }
                    return false;
                }
                return false;
            }
        }
    }
}

