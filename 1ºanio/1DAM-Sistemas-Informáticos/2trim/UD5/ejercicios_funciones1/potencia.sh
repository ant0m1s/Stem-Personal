potencia() { 
    validar_argumentos 2 $@ || return 1
    resultado=$(( $1 ** $2 ))
    echo $resultado
}