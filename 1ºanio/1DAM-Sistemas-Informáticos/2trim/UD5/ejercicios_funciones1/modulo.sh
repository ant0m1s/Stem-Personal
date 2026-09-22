modulo() {
    validar_argumentos 2 $@ || return 1
    if [$2 -eq 0]; then
    echo "No se puede dividir por 0"
    fi

    resultado=$(( $1 % $2  ))
    echo $resultado
}