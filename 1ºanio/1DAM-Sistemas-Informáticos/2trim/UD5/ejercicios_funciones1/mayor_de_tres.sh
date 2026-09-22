mayor_de_tres() {
    validar_argumentos 2 $@ || return 1
    mayor= $1
    if [$2 -eq $mayor]; then
        mayor= $2
    fi
    if [$3 -eq $mayor]; then
        mayor= $3
    fi

    echo "Este es el nº más alto= $mayor"
}