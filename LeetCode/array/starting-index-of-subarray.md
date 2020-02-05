## [Starting Index of Subarray](https://leetcode.com/discuss/interview-question/402692/Microsoft-or-On-Campus-Interview-or-Starting-Index-Of-Subarray)

![](https://github.com/weltond/DataStructure/blob/master/medium.PNG)

Given two integer arrays, find the first starting point of one array in the second array. Basically, second array is a subarray in the first array and you have to return the index of the starting point of the subarray. If subarray doesn't exist, then return -1.

Example:

```
Input:
[1,2,4,5,7,8]
[4,5]
Output: 2
```

```
Input:
[1,2,2,2,2,2,2,3]
[2,2,2,3]
Output: 4
```

## Answer
### Method 1 - Two Pointer - 

```java
    private static int findStartIndex(int[] arr1, int[] arr2){

        if(arr1.length == 0 || arr2.length == 0){return -1;}

        int maxL = Math.max(arr1.length, arr2.length);
        int minL = Math.min(arr1.length, arr2.length);
        boolean isArr1Longer = false;
        
        if(arr1.length >= arr2.length){

                isArr1Longer = true;
        }

        int[] arrRef1; // this is always >= arrRef2
        int[] arrRef2; // this is always <= arrRef1

        if(isArr1Longer){
            arrRef1 = arr1;
            arrRef2 = arr2;
        } else {
            arrRef1 = arr2;
            arrRef2 = arr1;
        }

        int i=0;
        int j=0;
        int pos = 0;


        while(i<maxL && j< minL){
            
                if(arrRef1[i++] == arrRef2[j]){
                    j++;
                } else {

                    i = i-j;
                    pos = i;
                    j=0;
                }

        }

        return j==0 ? -1 : pos;

    }


    public static void main(String[] args) {

        System.out.println(findStartIndex(new int[]{1,2,4,5,7,8}, new int[]{4,5}));
        System.out.println(findStartIndex(new int[]{2, 2, 2, 3}, new int[]{1, 2, 2, 2, 2, 2, 2, 3}));
    } 
```
