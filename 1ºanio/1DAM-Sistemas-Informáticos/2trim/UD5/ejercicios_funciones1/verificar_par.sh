verificar_par() {
    validar_argumentos 1 $@ || return 1
    if [$(($1 % 2)) -eq 0 ]; then
        echo "$1 es par"
    else
        echo "$1 es par"
    fi
}