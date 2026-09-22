*script*
mkdir ejercicio1
cd ejercicio1;
mkdir documentacion1 documentacion2;
touch archivo1.txt archivo2.txt programa1.sh programa2.sh carpetas programas archivos;
*script*

*script para hacer log carpetas, archivos y programas*
find ejercicio1 -type d > carpetas
find . -type f "*.txt" > archivos
find . -type f "*.sh" > programas
*script para hacer log carpetas, archivos y programas*

*script*
cat archivo2.txt archivo1.txt > temp.txt;
cat temp.txt > archivo2.txt;
*script*

*script*
cd ..
mkdir Carpetas Archivos Programas
mv ./ejercicio1/ Carpetas
mv *.txt Archivos
mv *.sh Programas
*script*