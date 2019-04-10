using System;
namespace HundirLaFlota
{
    /*
    * Ferrer Rodríguez, Francisco José
    * Practica Evaluable Tema 7
    *
    * La clase Tablero implementará lo necesario para trabajar posiciones
    * de la clase Casilla, y a su vez barcos de la clase Barco
    * Así como todas las implementaciones necesarias para poder trabajar con el tablero y el juego   
    */
    public class Tablero
    {
        public static int width = 8;
        public static int height = 8;
        private Casilla[,] tablero;
        private Barco[] barcos;

        public Tablero()
        {
            // Inicializamos tablero y seteamos casillas
            EstablecerTablero();

            // Inicializamos y seteamos barcos
            EstablecerBarco(new Portaaviones(), new Buque(), new Fragata(), new Lancha());

            /*PonerBarco(Barco.Tipo.PORTAAVIONES, Barco.Orientacion.HORIZONTAL, 4, 4);
            PonerBarco(Barco.Tipo.PORTAAVIONES, Barco.Orientacion.VERTICAL, 4, 5);
            PonerBarco(Barco.Tipo.BUQUE, Barco.Orientacion.VERTICAL, 4, 5);*/
        }

        public void EstablecerTablero()
        {
            tablero = new Casilla[width, height];
            for (int y = 0; y < tablero.GetLength(0); y++)
                for (int x = 0; x < tablero.GetLength(1); x++)
                    tablero[x, y] = new Casilla(new Casilla.Coords(x, y));
        }

        public void EstablecerBarco(Portaaviones portaviones, Buque buque, Fragata fragata, Lancha lancha)
        {
            barcos = new Barco[4];
            barcos[0] = portaviones;
            barcos[1] = buque;
            barcos[2] = fragata;
            barcos[3] = lancha;
        }

        public Casilla GetPieza(int x, int y)
        {
            return tablero[x, y];
        }

        public Barco GetBarcoTablero(Barco.Tipo tipo_barco)
        {
            foreach (Barco barco in barcos)
                if(barco.GetTipo().Equals(tipo_barco))
                    return barco;
            
            return null;
        }

        public Barco GetBarcoTablero(int x, int y)
        {
            foreach (Barco barco in barcos)
                foreach(Casilla casilla in barco.GetCasillas())
                    if(null != casilla)
                        if (casilla.GetCoords().fila == x && casilla.GetCoords().columna == y)
                            return barco;

            return null;
        }

        public bool TodosHundidos()
        {
            bool hundidos = true;

            foreach(Barco barco in barcos)
            {
                if(null == barco || !barco.EstaHundido())
                {
                    hundidos = false;
                    break;
                }
            }

            return hundidos;
        }

        private bool PonerBarco(Barco.Tipo tipo_barco, Barco.Orientacion orientacion, int x, int y)
        {
            bool puesto = false;

            // Console.WriteLine("Tipo: {0} | Orientación: {1} | x: {2} | y: {3}", tipo_barco, orientacion, x, y);

            // Comprobamos que no excede los límites
            if ((orientacion.Equals(Barco.Orientacion.HORIZONTAL) &&
                x + GetBarcoTablero(tipo_barco).GetTamano() <= tablero.GetLength(0)) || 
                (orientacion.Equals(Barco.Orientacion.VERTICAL) && 
                y + GetBarcoTablero(tipo_barco).GetTamano() <= tablero.GetLength(1)))
            {
                int p = orientacion.Equals(Barco.Orientacion.HORIZONTAL) ? x : y;

                // Comprobamos que todas las posiciones para el nuevo barco son de tipo agua
                puesto = true;
                for (int i = p; i < GetBarcoTablero(tipo_barco).GetTamano() + p; i++)
                    if ((orientacion.Equals(Barco.Orientacion.HORIZONTAL) && !tablero[i, y].GetEstado().Equals(Casilla.Estados.AGUA)) ||
                         orientacion.Equals(Barco.Orientacion.VERTICAL) && !tablero[x, i].GetEstado().Equals(Casilla.Estados.AGUA))
                    {
                        puesto = false;
                        break;
                    }

                // Si no excede límites y todas son de tipo agua, establecemos el tipo de barco en el tablero
                if (puesto)
                {
                    int array_pos = 0;
                    Casilla casilla = null;
                    for (int i = p; i < GetBarcoTablero(tipo_barco).GetTamano() + p; i++)
                    {
                        if (orientacion.Equals(Barco.Orientacion.HORIZONTAL))
                            casilla = tablero[i, y];
                        else if (orientacion.Equals(Barco.Orientacion.VERTICAL))
                            casilla = tablero[x, i];

                        casilla.SetEstado(Casilla.Estados.BARCO);
                        GetBarcoTablero(tipo_barco).SetCasillas(array_pos, casilla);
                        array_pos++;
                    }

                }
            }

            return puesto;
        }

        public void Rellenar()
        {
            int x;
            int y;
            char pos;
            Barco.Orientacion orientacion;
            bool establecido;
            foreach (Barco barco in barcos)
            {
                establecido = false;
                do {
                    Console.WriteLine("** Rellenando barco: {0} **", barco.GetTipo());

                    // falta comprobar que las posiciones no excedan ni sean menores
                    Console.WriteLine("\nIntroduce fila (1 - {0}):", width);
                    while (!int.TryParse(Console.ReadLine(), out x) || x < 1 || x > width)
                        Console.WriteLine("Error, debe introducir un número válido para la fila. Vuelva a intentarlo:");

                    // aqui tambien
                    Console.WriteLine("\nIntroduce columna (1 - {0}):", height);
                    while (!int.TryParse(Console.ReadLine(), out y) || y < 1 || y > height)
                        Console.WriteLine("Error, debe introducir un número válido para la columna. Vuelva a intentarlo:");

                    // falta comprobar esto bien
                    Console.WriteLine("\nOrientación horizontal? (S/N):");
                    int n = 0;
                    do
                    {
                        if (n > 0)
                            Console.WriteLine("Error, debe introducir S/N. Vuelva a intentarlo");
                            
                        pos = Console.ReadLine()[0];

                        orientacion = string.Equals(pos.ToString(), "S", StringComparison.OrdinalIgnoreCase) ? 
                            Barco.Orientacion.HORIZONTAL : Barco.Orientacion.VERTICAL;
                        n++;
                    } while (!string.Equals(pos.ToString(), "S", StringComparison.OrdinalIgnoreCase) && 
                        !string.Equals(pos.ToString(), "N", StringComparison.OrdinalIgnoreCase));

                    if (PonerBarco(barco.GetTipo(), orientacion, x - 1, y - 1))
                    {
                        establecido = true;
                        Console.WriteLine("BARCO ESTABLECIDO CORRECTAMENTE\n");
                    }
                    else
                        Console.WriteLine("NO CABE {0}\n", orientacion);
                } while (!establecido);
            }

        }

        public void Generar()
        {
            bool establecido;
            Random rnd = new Random();

            Console.WriteLine("GENERANDO BARCOS DEL...");
            foreach (Barco.Tipo tipo in Enum.GetValues(typeof(Barco.Tipo)))
            {
                establecido = false;
                do
                {
                    Array values = Enum.GetValues(typeof(Barco.Orientacion));
                    Barco.Orientacion orientacion = (Barco.Orientacion)values.GetValue(rnd.Next(0, 2));

                    if (PonerBarco(tipo, orientacion, rnd.Next(0, width), rnd.Next(0, height)))
                    {
                        establecido = true;
                        Console.WriteLine("BARCO ESTABLECIDO CORRECTAMENTE\n");
                    }
                    else
                        Console.WriteLine("NO CABE {0}\n", orientacion);

                } while (!establecido);
            }

            Console.WriteLine("BARCOS DEL ORDENADOR ESTABLECIDOS CORRECTAMENTE\n");
        }

        public void Mostrar()
        {
            for (int y = 0; y < tablero.GetLength(0); y++)
            {
                for (int x = 0; x < tablero.GetLength(1); x++)
                    Console.Write((char)tablero[x, y].GetEstado() + " ");
                Console.WriteLine();
            }
        }

        public void MostrarOcuto()
        {
            for (int y = 0; y < tablero.GetLength(0); y++)
            {
                for (int x = 0; x < tablero.GetLength(1); x++)
                    Console.Write(!tablero[x, y].GetEstado().Equals(Casilla.Estados.BARCO) ?
                         (char)tablero[x, y].GetEstado() + " " : ". ");
                Console.WriteLine();
            }
        }

    }
}