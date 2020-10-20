// MultiplyInterface.aidl
package com.example.aidl;

// Declare any non-default types here with import statements

interface MultiplyInterface {
    //AIDL is used for creating a relation between client(ex : MainActivity) and remoteserver(ex : class)
    int multiplyTwoValues(int firstnum, int scndnum);
}