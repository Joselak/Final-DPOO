#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>


#define CANT_FACULTADES 9
#define MATRICULA 4

//ESTRUCTURA PARA MOSTRAR LOS DATOS ALMACENADOS
struct consulta{
    char nombre_Facultad[50];
    int  matricula_Antigua[4];
    int  cant_antigua_L;
    int  cant_antigua_C;
    int cant_antigua_CH;
    char nombre_Tecnico[50];
}consulta[9];

//ESTRUCTURA PARA INGRESAR Y GUADAR LOS DATOS
struct facultad{

    char nombre[50];
    int matricula[4];
    int cant_laboratorios;
    int cant_computadoras;
    int cant_habilitadas;
    char nombre_tecnico[50];

}facultad[9];

//INTERFAZ-------------------------------------------------------------------------------------------------


//Salida de Datos
//--------1ra OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------1ra OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------1ra OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------


//OPCION 2 DEL SUBMENU
void facultadConMasComputadoras(int *num_facultades) {

    int max_computadoras = 0;
    int indice_facultad = -1;
    int k;

    //Llamada a la funcion de logica
    indice_facultad=buscarPor_Facultad(num_facultades, max_computadoras);

    // Si se encontra lo buscado , se imprimen sus datos generales
    if (indice_facultad != -1) {
        printf("\nFacultad que mas computadoras tiene:");
        printf("\n---------------------------------------------------\n");
        printf("---------------------------------------------------\n");
        printf("Nombre de la facultad: %s\n", facultad[indice_facultad].nombre);

        printf("---------------------------------------------------\n");
        for(k=0;k<4;k++){
            printf("Matricula del a\244o (%d): %d estudiantes\n",k+1,facultad[indice_facultad].matricula[k]);
        }

        printf("Cantidad de laboratorios: %d\n", facultad[indice_facultad].cant_laboratorios);
        printf("Cantidad total de computadoras: %d\n", facultad[indice_facultad].cant_computadoras);
        printf("Cantidad de computadoras habilitadas: %d\n", facultad[indice_facultad].cant_habilitadas);
        printf("Nombre del t\202cnico: %s\n", facultad[indice_facultad].nombre_tecnico);
    } else {
        printf("No se encontraron facultades que cumplan con los requisitos.\n");
    }
}




//OPCION 3 DEL SUBMENU
void mostrarDatos(int *num_facultades){

    int cont;
    int i;


for(cont=0;cont<*num_facultades;cont++){

    printf("---------------------------------------------------\n");
    printf("---------------------------------------------------\n");
    //MOSTRAR EL NOMBRE DE LA FACULTAD
    printf("Nombre de la facultad %d :%s",cont+1,facultad[cont].nombre);


    //MOSTRAR LA MATRICULA POR AÑO
    printf("\n---------------------------------------------------\n");
    for(i=0;i<4;i++){
        printf("\nMatricula del a\244o (%d): %d estudiantes",i+1,facultad[cont].matricula[i]);
    }


    //MOSTRAR LA CANTIDAD DE LABORATORIOS
    printf("\nCantidad de laboratorios:%d",facultad[cont].cant_laboratorios);

    //MOSTRAR LA CANTIDAD TOTAL DE COMPUTADORAS
    printf("\nCantidad total de computadoras:%d",facultad[cont].cant_computadoras);

    //MOSTRAR LA CANTIDAD COMPUTADORAS HABILITADAS
    printf("\nCantidad de computadoras habilitadas:%d",facultad[cont].cant_habilitadas);

    //MOSTRAR EL NOMBRE DEL TECNICO ENCARGADO
    printf("\nT\202cnico encargado:%s\n\n",facultad[cont].nombre_tecnico);

}

}


//OPCION 4 DEL SUBMENU
void facultades_Matricula_Mayor( int *num_facultades) {
    int i;
    int capacidad_total[9];
    int alguna_cumple=0;


    buscarCapacidad(num_facultades, capacidad_total);

    // Mostrar las facultades donde la matrícula es mayor que la capacidad
    printf("Facultades donde la matr\241cula del a\244o es mayor que la capacidad:\n");
        for (i = 0; i < *num_facultades; i++) {
            if (facultad[i].matricula[3] > capacidad_total[i]) {
                printf("La facultad '%s' tiene una matricula que excede a la capacidad.\n", facultad[i].nombre);
                alguna_cumple = 1;  // Al menos una facultad cumple
            } else {
                printf("La facultad '%s' no tiene una matricula que excede a la capacidad.\n", facultad[i].nombre);
            }
        }

        printf("------------------------------------------------------------------------------------\n");

        // Resumen final
        if (alguna_cumple) {
            printf("Existen al menos una o varias facultades donde la matricula excede la capacidad.\n\n");
        } else {
            printf("Ninguna facultad excede la capacidad.\n\n");
        }
    }

//OPCION 5 DEL SUBMENU

void MasDe10Computadoras(int *num_facultades) {
    int i;
    int hay_tecnicos=0;




    printf("Los t\202cnicos que poseen m\240s de 10 computadoras:\n");
        for (i = 0; i < *num_facultades; i++) {
            if (facultad[i].cant_computadoras > 10) {
                printf("T\202cnico: %s\n", facultad[i].nombre_tecnico);
                hay_tecnicos = 1; // Marcar que hay al menos un técnico con más de 10 computadoras
            }
        }if (!hay_tecnicos) {
            printf("No hay t\202cnicos con m\240s de 10 computadoras.\n");
        }
    }


//OPCION 6 DEL SUBMENU

void MasDeTresLaboratorios(int *num_facultades) {
    int i;
    int alguna_cumple = 0;  // Variable para ver si alguna facultad cumple




    printf("\nResultados de la verificaci\243n:\n");
    printf("----------------------------------------------------------------------------------\n");
    for (i = 0; i < *num_facultades; i++) {
        if (facultad[i].cant_laboratorios > 3) {
            printf("La facultad '%s' tiene m\240s de 3 laboratorios con computadoras disponibles.\n", facultad[i].nombre);
            alguna_cumple = 1;  // Al menos una facultad cumple

        } else {
            printf("La facultad '%s' NO tiene m\240s de 3 laboratorios con computadoras disponibles.\n", facultad[i].nombre);
        }
    }

    printf("------------------------------------------------------------------------------------\n");

    // Resumen final
    if (alguna_cumple) {
        printf("Existen al menos una o varias facultades que tiene m\240s de 3 laboratorios con computadoras disponibles.\n");
    } else {
        printf("Ninguna facultad tiene m\240s de 3 laboratorios con computadoras disponibles.\n");
    }
}

//--------2da OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------2da OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------2da OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------

void mostrar_Datos(){
    //OPCION 3
    printf("\nDATOS DEL CURSO ANTERIOR\n");
    int i,k;


    for(i=0;i<CANT_FACULTADES;i++){
        printf("---------------------------------------------------\n");
        printf("Nombre de la facultad: %s\n",consulta[i].nombre_Facultad);
        printf("---------------------------------------------------\n");
        for(k=0;k<4;k++){
            printf("Matricula del a\244o (%d): %d estudiantes\n",k+1,consulta[i].matricula_Antigua[k]);
        }

        printf("Cantidad de laboratorios: %d\n", consulta[i].cant_antigua_L);
        printf("Cantidad total de computadoras: %d\n", consulta[i].cant_antigua_C);
        printf("Cantidad de computadoras habilitadas: %d\n", consulta[i].cant_antigua_CH);
        printf("Nombre del t\202cnico: %s\n", consulta[i].nombre_Tecnico);

        system("pause");

    }



    //OPCION 2
    printf("\n\n---------------------------------------------------\n");
    printf("FACULTAD QUE MAS COMPUTADORAS TIENE:\n\n");

    int max_computadoras = 0;
    int indice_facultad = -1;



    //Llamada a la funcion de logica
    indice_facultad=buscar_Por_Facultad(max_computadoras);

    // Si se encontra lo buscado , se imprimen sus datos generales
    if (indice_facultad != -1) {


        printf("Nombre de la facultad: %s\n",consulta[indice_facultad].nombre_Facultad);
        for(k=0;k<MATRICULA;k++){
            printf("Matricula del a\244o (%d): %d estudiantes\n",k+1,consulta[indice_facultad].matricula_Antigua[k]);
        }

        printf("Cantidad de laboratorios: %d\n", consulta[indice_facultad].cant_antigua_L);
        printf("Cantidad total de computadoras: %d\n", consulta[indice_facultad].cant_antigua_C);
        printf("Cantidad de computadoras habilitadas: %d\n", consulta[indice_facultad].cant_antigua_CH);
        printf("Nombre del t\202cnico: %s\n", consulta[indice_facultad].nombre_Tecnico);
    } else {
        printf("No se encontraron facultades.\n");
    }

    system("pause");



    //OPCION 4
    int capacidad_total[9];


    buscar_Capacidad(capacidad_total);

    // Mostrar las facultades donde la matri­cula es mayor que la capacidad
    printf("---------------------------------------------------\n");
    printf("FACULTAD(s) DONDE LA MATRICULA ES MAYOR QUE LA CAPACIDAD DE COMPUTADORAS:\n\n");
    for (i = 0; i < CANT_FACULTADES; i++) {
        if (consulta[i].matricula_Antigua[3] > capacidad_total[i]) {
            printf("-%s.\n",consulta[i].nombre_Facultad);
          }
        }
    system("pause");


        //OPCION 5
        int hay_tecnicos=0;

        printf("---------------------------------------------------\n");
        printf("LOS TECNICOS QUE POSEEN MAS DE 10 COMPUTADORAS SON:\n\n");
        for (i = 0; i < CANT_FACULTADES; i++) {
            if (consulta[i].cant_antigua_C > 10) {
                printf("-%s\n", consulta[i].nombre_Tecnico);
                hay_tecnicos = 1; // Marcar que hay al menos un tecnico con mas de 10 computadoras
            }
        }if (!hay_tecnicos) {
            printf("No hay t\202cnicos con m\240s de 10 computadoras.\n");
        }

        system("pause");



    //OPCION 6
            printf("----------------------------------------------------------------------------------\n");

       int hay_facultades = 0;

          for (i = 0; i < CANT_FACULTADES; i++) {
              if (consulta[i].cant_antigua_L > 3) {
                  if (!hay_facultades) {
                 printf("LA(s) FACULTADES QUE CUENTAN CON MAS DE 3 LABORATORIOS SON:\n\n");
                   hay_facultades = 1;
             }
                 printf("-%s \n", consulta[i].nombre_Facultad);
              }
                }
                if (!hay_facultades) {
                    printf("Ninguna facultad tiene más de 3 laboratorios con computadoras disponibles.\n\n");
                }
                printf("\n");
       }







//Menu
void menu(){

    int num_facultades;
    int opcion;

    system("cls");
    printf("Ingrese el n\243mero de facultades a registrar: ");
    while(scanf("%d",&num_facultades)!=1 || num_facultades<0 || num_facultades>9 || num_facultades==0){
        printf("Error, vuelva a intentar\n\n");
        while(getchar()!='\n');

        printf("Ingrese el n\243mero de facultades a registrar: ");

    }


  do{

    system("cls");
    printf("BIENVENIDO A REGISTRO Y VERIFICACION DE NUEVOS DATOS.\n\n");
    printf("     Opciones \n");
    printf("1. Registro de datos \n");
    printf("2. Mostrar los datos de la facultad que tenga la mayor cantidad de computadoras\n");
    printf("3. Mostrar datos de las facultades\n");
    printf("4. Verificaci\242n de matricula y disponibilidad\n");
    printf("5. T\202cnicos que posean m\240s de 10 computadoras\n");
    printf("6. Verificar si hay alguna facultad que tenga m\240s de 3 laboratorios con computadoras disponibles\n");
    printf("7. Volver al menu principal \n");

    printf("\nIngrese una opci\242n: ");
    fflush(stdin);
    opcion = getchar();
    switch (opcion) {
      case '1':
        ingresarDatos(&num_facultades);
        system("pause");
        break;
      case '2':
         facultadConMasComputadoras(&num_facultades);
         system("pause");
        break;
      case '3':
         mostrarDatos(&num_facultades);
         system("pause");
        break;
      case '4':
         facultades_Matricula_Mayor(&num_facultades);
         system("pause");
        break;
      case '5':
         MasDe10Computadoras(&num_facultades);
         system("pause");
        break;
      case '6':
         MasDeTresLaboratorios(&num_facultades);
         system("pause");
        break;
      case '7':

        break;
      default:
         printf("Opci\242n err\242nea.Intente de nuevo. \n");
          system("pause");
    }       
    }while (opcion != '7' );
  }

void Menu_Principal(){

    int opcion;

    system("cls");
    printf("\nBienvenido(a) al Sistema de Registro e Identificaci\242n de Laboratorios de la Cujae(S.R.I.L.C)\n\n");

    printf("Recuerde: si va a trabajar con datos nuevos , primero debe registrarlos.\n\n");
    system("pause");

    do{

        system("cls");
        printf("     Opciones \n");
        printf("1. Registro y verificaci\242n de nuevos datos. \n");
        printf("2. Revisar los datos del a\244o anterior.\n");
        printf("3. Terminar programa.\n");

        printf("Ingrese una opci\242n: ");        
        while(scanf("%d", &opcion) != 1 ) {
            printf("Opci\242n err\242nea. Intente de nuevo. \n");

            while (getchar() != '\n');
        }
        switch (opcion) {
        case 1:
            menu();
            system("pause");
            break;
        case 2:
            system("cls");
            printf("BIENVENIDO A LA REVISION DE DATOS.\n");
            mostrar_Datos();
            system("pause");
            break;
        case 3:

            break;
        default:
            printf("Opci\242n err\242nea.Intente de nuevo. \n");
             system("pause");

        }
    }while(opcion != 3 );
}






//Entrada de usuario
//OPCION 1 DEL SUBMENU OPCIONES
void ingresarDatos(int *num_facultades){

    int cont;

    for(cont=0;cont<*num_facultades;cont++){

printf("----------------------------------------------------\n");
printf("----------------------------------------------------");
//INGRESAR EL NOMBRE DE LA FACULTAD

    do {
        printf("\nIngrese el nombre de la facultad %d : ",cont+1);
        fflush(stdin);
        fgets(facultad[cont].nombre, sizeof(facultad[cont].nombre), stdin);

        // Eliminar el salto de línea que fgets puede incluir
        facultad[cont].nombre[strcspn(facultad[cont].nombre, "\n")] = '\0';

        if (!validar_Facultad(cont)) {
            printf("Error: El nombre solo debe contener letras. Intente de nuevo.\n");
        }
    } while (!validar_Facultad(cont));


printf("----------------------------------------------------");
 //INGRESAR LA MATRICULA POR AÑO

     int i;

    for(i=0;i<4;i++){
        printf("\nIngrese la matricula del a\244o (%d):",i+1);
    while(scanf("%d",&facultad[cont].matricula[i])!=1 || facultad[cont].matricula[i]<0 ){
        printf("Error, vuelva a intentar.\n\n");
        while(getchar()!='\n');
        printf("\nIngrese la matricula del a\244o %d :",i+1);
    }
  }

printf("----------------------------------------------------");
//INGRESAR CANTIDAD DE LABORATORIOS


    printf("\nIngrese la cantidad de laboratorios en la facultad:");
    while(scanf("%d",&facultad[cont].cant_laboratorios)!=1 || facultad[cont].cant_laboratorios<0){
        printf("Error, vuelva a intentar.\n\n");
        while(getchar()!='\n');
        printf("\nIngrese la cantidad de laboratorios en la facultad:");
    }

printf("----------------------------------------------------");
//INGRESAR CANTIDAD DE COMPUTADORAS

    printf("\nIngrese la cantidad total de computadoras :");
    while(scanf("%d",&facultad[cont].cant_computadoras)!=1 || facultad[cont].cant_computadoras<0){
        printf("Error, vuelva a intentar.\n\n");
        while(getchar()!='\n');
        printf("\nIngrese la cantidad de computadoras en total:");
    }

printf("----------------------------------------------------");
//INGRESAR LAS COMPUTADORAS HABILITADAS

    printf("\nIngrese la cantidad de computadoras habilitadas:");
    while(scanf("%d",&facultad[cont].cant_habilitadas)!=1 || facultad[cont].cant_habilitadas<0 || facultad[cont].cant_computadoras<facultad[cont].cant_habilitadas){
        printf("Error, el n\243mero de computadoras habilitadas no puede ser mayor que el total de las mismas.\n");
        printf("Vuelva a intenta.\n");
        while(getchar()!='\n');
        printf("\nIngrese la cantidad de computadoras habilitadas:");
    }

printf("----------------------------------------------------");
//INGRESAR EL NOMBRE DEL TECNICO QUE CUIDA EL LABORATORIO

    do {
        printf("\nIngrese el nombre del t\202cnico: ");
        fflush(stdin);
        fgets(facultad[cont].nombre_tecnico, sizeof(facultad[cont].nombre_tecnico), stdin);

        // Eliminar el salto de línea que fgets puede incluir
        facultad[cont].nombre_tecnico[strcspn(facultad[cont].nombre_tecnico, "\n")] = '\0';

        if (!validar_Tecnico(cont, facultad)) {
            printf("Error: El nombre solo debe contener letras. Intente de nuevo.\n");
        }
    } while (!validar_Tecnico(cont, facultad));

}

  printf("\nDatos guardados satisfactoriamente.\n\n");
}






/*LOGICA------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------*/


//--------1ra OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------1ra OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------1ra OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------


int buscarPor_Facultad(int *num_facultades,int max_computadoras){

      int indice_facultad = -1;
      max_computadoras = 0;



    // Recorrer todas las facultades
     int i;
     // Recorrer todas las facultades
     for ( i = 0; i < *num_facultades; i++) {
             if (facultad[i].cant_computadoras > max_computadoras) {
                 max_computadoras = facultad[i].cant_computadoras;
                 indice_facultad = i;

             }

     }return indice_facultad;
}



void buscarCapacidad(int *num_facultades, int capacidad_total[]) {
    int i;
    int resultado;

    for (i = 0; i < *num_facultades; i++) {
        resultado = facultad[i].cant_habilitadas * 2;
        capacidad_total[i] = resultado;
    }
}


//OPCION 2 DEL MENU PRINCIPAL

void inicializacionFacultad(){

        strcpy(consulta[0].nombre_Facultad, "Qu\241mica");
        strcpy(consulta[1].nombre_Facultad, "Industrial");
        strcpy(consulta[2].nombre_Facultad, "Civil");
        strcpy(consulta[3].nombre_Facultad, "Telecomunicaciones y Electr\242nica");
        strcpy(consulta[4].nombre_Facultad, "Autom\240tica y Biom\202dica");
        strcpy(consulta[5].nombre_Facultad, "Inform\240tica");
        strcpy(consulta[6].nombre_Facultad, "Mec\240nica");
        strcpy(consulta[7].nombre_Facultad, "Arquitectura");
        strcpy(consulta[8].nombre_Facultad, "El\202ctrica");

    }


void inicializarMatriculas() {
    int i,j;
    int matriculas[CANT_FACULTADES][MATRICULA] = {{90, 76, 90, 57}, {32, 10, 45, 57}, {67, 87, 65, 57},
                          {100, 120, 89, 90}, {20, 33, 43, 57}, {57, 42, 46, 70},
                            {21, 12, 27, 10}, {50, 51, 47, 39}, {146, 107, 99, 57}
        };

        for (i = 0; i < CANT_FACULTADES; i++) {
            for ( j = 0; j < MATRICULA; j++) {
                consulta[i].matricula_Antigua[j] = matriculas[i][j];
            }
        }

    }


void inicializarLaboratorios(){
    int i;
    int laboratorios[CANT_FACULTADES] = {5, 4, 6, 2, 9, 3, 3, 7, 2};

        for (i = 0; i < CANT_FACULTADES; i++) {
            consulta[i].cant_antigua_L = laboratorios[i];
        }

    }



void inicializarComputadoras(){
    int i;
    int computadoras[CANT_FACULTADES] = {30, 12, 38, 17, 50, 24, 28, 41, 20};

        for (i = 0; i < CANT_FACULTADES; i++) {
            consulta[i].cant_antigua_C = computadoras[i];
        }

    }


void inicializarComputadorasH(){
    int i;
    int computadorasH[CANT_FACULTADES] = {25, 10, 27, 8, 40, 19, 20, 38, 20};
        for (i = 0; i < CANT_FACULTADES; i++) {
            consulta[i].cant_antigua_CH = computadorasH[i];
        }

    }


void inicializacionTecnico(){

        strcpy(consulta[0].nombre_Tecnico, "Alejandro Vidal");
        strcpy(consulta[1].nombre_Tecnico, "Alberto Fonseca");
        strcpy(consulta[2].nombre_Tecnico, "Carlos Mart\241nez");
        strcpy(consulta[3].nombre_Tecnico, "Mart\241n Hechevarria");
        strcpy(consulta[4].nombre_Tecnico, "Amanda Rodr\241guez");
        strcpy(consulta[5].nombre_Tecnico, "Daniel Garc\241a");
        strcpy(consulta[6].nombre_Tecnico, "Daniela Berman");
        strcpy(consulta[7].nombre_Tecnico, "Ram\242n Bravo");
        strcpy(consulta[8].nombre_Tecnico, "Diego Gonz\240lez");

    }

//--------2da OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------2da OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------
//--------2da OPCION DEL MENU PRINCIPAL-----------------------------------------------------------------


int buscar_Por_Facultad(){

      int indice_facultad = -1;
      int max_computadoras = 0;

     int i;
     // Recorrer todas las facultades
     for ( i = 0; i < CANT_FACULTADES; i++) {
             if (consulta[i].cant_antigua_C > max_computadoras) {
                 max_computadoras = consulta[i].cant_antigua_C ;
                 indice_facultad = i;

             }

     }return indice_facultad;
}




void buscar_Capacidad(int capacidad_total[]) {
    int i;
    int resultado;

    for (i = 0; i < CANT_FACULTADES; i++) {
        resultado = consulta[i].cant_antigua_CH* 2;
        capacidad_total[i] = resultado;
    }
}


int validar_Tecnico(int cont) {
    int i;
    for (i = 0; i < strlen(facultad[cont].nombre_tecnico); i++) {
      if (!isalpha((unsigned char)facultad[cont].nombre_tecnico[i]) && facultad[cont].nombre_tecnico[i] != ' ') { // Permitir espacios
         return 0; // No es una letra
        }
    }
    return 1; // Es una cadena válida
}

int validar_Facultad(int cont) {
    int i;
    for (i = 0; i < strlen(facultad[cont].nombre); i++) {
      if (!isalpha((unsigned char)facultad[cont].nombre[i]) && facultad[cont].nombre[i] != ' ') { // Permitir espacios
         return 0; // No es una letra
        }
    }
    return 1; // Es una cadena válida
}



/*MAIN---------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------*/



int main(void)
{

   /*Llamada a las funciones que inicializan los datos a
       mostrar de la opcion 2 del menu principal*/
    inicializacionFacultad();
    inicializarMatriculas();
    inicializarLaboratorios();
    inicializarComputadoras();
    inicializarComputadorasH();
    inicializacionTecnico();

   //Llamada a la funcion del menu principal y el segundario
    Menu_Principal();


    return 0;
}



