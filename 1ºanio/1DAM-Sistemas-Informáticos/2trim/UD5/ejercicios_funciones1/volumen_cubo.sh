volumen_cubo() {
    validar_argumentos 1 $@ || return 1
    resultado=$(( $1 ** 3 ))
    echo $resultado
}