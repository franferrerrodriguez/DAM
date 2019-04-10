using System;
namespace HundirLaFlota
{
    /*
    * Ferrer Rodríguez, Francisco José
    * Practica Evaluable Tema 7
    * Apartado 1 si
    * Apartado 2 si
    * Apartado 3 si
    * Apartado 4 si
    *
    * La clase Juego será la clase principal que contendrá el Main
    * Se encargará de turnar a los jugadores e implementar la lógica 
    * de las demás clases   
    */
    class Juego
    {
        public static void Main(string[] args)
        {
            Tablero tablero_jugador = new Tablero();
            Tablero tablero_ordenador = new Tablero();

            tablero_jugador.Rellenar();

            tablero_ordenador.Generar();

            Console.WriteLine("Proceso finalizado. Pulsa Intro para comenzar!");
            Console.ReadKey();

            bool ganador = false;
            bool turno_jugador = true;
            Barco barco_actual;
            Casilla casilla_actual;
            int x;
            int y;
            do
            {
                Console.Clear();

                if (turno_jugador)
                {
                    Console.WriteLine("Turno del jugador.");

                    Console.WriteLine("Introduce fila (1 - {0}):", Tablero.width);

                    while (!int.TryParse(Console.ReadLine(), out x) || x < 1 || x > Tablero.width)
                        Console.WriteLine("Error, debe introducir un número válido para la fila. Vuelva a intentarlo:");

                    Console.WriteLine("Introduce fila (1 - {0}):", Tablero.height);

                    while (!int.TryParse(Console.ReadLine(), out y) || y < 1 || y > Tablero.height)
                        Console.WriteLine("Error, debe introducir un número válido para la columna. Vuelva a intentarlo:");

                    x -= 1;
                    y -= 1;

                    casilla_actual = tablero_ordenador.GetPieza(x, y);
                    if (casilla_actual.GetEstado().Equals(Casilla.Estados.BARCO))
                        casilla_actual.SetEstado(Casilla.Estados.TOCADO);
                    else
                        Console.WriteLine("AGUA");

                    barco_actual = tablero_ordenador.GetBarcoTablero(x, y);
                    if(null != barco_actual)
                        if (barco_actual.EstaHundido())
                            Console.WriteLine("TOCADO Y HUNDIDO");
                        else if (barco_actual.EstaTocado())
                            Console.WriteLine("TOCADO");
                            
                    tablero_ordenador.MostrarOcuto();

                    if (tablero_ordenador.TodosHundidos())
                        ganador = true;
                    else
                    {
                        turno_jugador = false;
                        Console.WriteLine("Pulsa Intro para continuar...");
                        Console.ReadKey();
                    }

                }
                else
                {
                    Console.WriteLine("Turno del ordenador.");

                    Random rnd = new Random();
                    x = rnd.Next(0, Tablero.width);
                    y = rnd.Next(0, Tablero.height);

                    Console.WriteLine("Fila {0}, Columna {1}", y + 1, x + 1);

                    casilla_actual = tablero_jugador.GetPieza(x, y);
                    if (casilla_actual.GetEstado().Equals(Casilla.Estados.BARCO))
                        casilla_actual.SetEstado(Casilla.Estados.TOCADO);
                    else
                        Console.WriteLine("AGUA");

                    barco_actual = tablero_jugador.GetBarcoTablero(x, y);
                    if (null != barco_actual)
                        if (barco_actual.EstaHundido())
                            Console.WriteLine("TOCADO Y HUNDIDO");
                        else if (barco_actual.EstaTocado())
                            Console.WriteLine("TOCADO");
                            
                    tablero_jugador.Mostrar();

                    if (tablero_jugador.TodosHundidos())
                        ganador = true;
                    else
                    {
                        turno_jugador = true;
                        Console.WriteLine("Pulsa Intro para continuar...");
                        Console.ReadKey();
                    }

                }
            } while (!ganador);

            tablero_jugador.Mostrar();
            tablero_ordenador.Mostrar();

            if (turno_jugador)
                Console.WriteLine("\nGANO EL JUGADOR");
            else
                Console.WriteLine("\nGANO EL ORDENADOR");

            Console.ReadKey();
        }

    }
}