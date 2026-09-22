#!/bin/bash 
division(){
    resultado=$(($1 / $2));
    echo $resultado
}
division=$(division 9 3)
echo "La división es: $division"