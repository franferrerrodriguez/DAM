using System;

/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 5
Ejercicio 2
Apartado 2.1 si
Apartado 2.2 si
Apartado 2.3 si
Apartado 2.4 si
Apartado 2.5 si
Apartado 2.6 si
*/
namespace Camino
{
    class Program
    {
        static void Main(string[] args)
        {
            int F;
            int C;
            if (args.Length == 2 && Int32.TryParse(args[0], out F) &&  Int32.TryParse(args[1], out C) &&  1 <= F && C > 0 && C <= 20)
            {
                char[,] posiciones = new char[F, C];
                char caracter;
                bool repetir = false;
                for (int x = 0; x < F; x++)
                {
                    for (int y = 0; y < C; y++)
                    {
                        repetir = false;
                        while (!repetir)
                        {
                            Console.Write(string.Format("\nIntroduce la posición ({0},{1}): ", x, y));
                            try
                            {
                                caracter = Convert.ToChar(Console.ReadLine());

                                if (caracter == 'E' && !ComprobarEntradaSalida(caracter, posiciones))
                                {
                                    Console.WriteLine("Ya hay una entrada 'E' establecida. Introduce otra posición.\n");
                                }
                                else if (caracter == 'S' && !ComprobarEntradaSalida(caracter, posiciones))
                                {
                                    Console.WriteLine("Ya hay una salida 'S' establecida. Introduce otra posición.\n");
                                }
                                else if(caracter == '+' || caracter == '.' || caracter == 'E' || caracter == 'S')
                                {
                                    posiciones[x, y] = caracter;
                                    repetir = true;
                                }
                                else
                                {
                                    Console.WriteLine("Error. Debe introducir un carácter válido: ['+', '.', 'E', 'S'].");
                                }
                            }
                            catch (FormatException)
                            {
                                Console.WriteLine("Error. Debe introducir un carácter válido: ['+', '.', 'E', 'S'].");
                                repetir = false;
                            }
                        }
                    }
                }

                // Generacion del tablero
                Casilla[,] tablero = ConstruirTablero(posiciones);

                // Creo el laberinto
                Laberinto laberinto = new Laberinto(tablero);

                // Mostramos laberinto
                MostrarPosiciones(posiciones);

                // Comenzamos recursividad
                ComprobarCamino(laberinto, tablero[0, 0]);

                // Muestro las soluciones
                if (laberinto.GetCamino())
                {
                    Console.WriteLine("SI hay camino");
                }
                else
                {
                    Console.WriteLine("NO hay camino");
                }

            }
            else
            {
                Console.WriteLine("Parámetros incorrectos.\nUso: Camino<filas> <columnas>");
            }

            Console.ReadKey();

        }

        // Construimos el tablero a partir de las posiciones solicitadas al usuario
        public static Casilla[,] ConstruirTablero(char[,] pos)
        {
            Casilla[,] tablero = new Casilla[pos.GetLength(0), pos.GetLength(1)];

            for (int x = 0; x < pos.GetLength(0); x++)
            {
                for (int y = 0; y < pos.GetLength(1); y++)
                {
                    bool arriba = false, derecha = false, abajo = false, izquierda = false, inicio = false, fin = false;

                    // Comprobamos arriba
                    if (x - 1 >= 0)
                    {
                        if (pos[x - 1, y] == 'E' || pos[x - 1, y] == 'S' || pos[x - 1, y] == '.')
                        {
                            arriba = true;
                        }
                    }

                    // Comprobamos derecha
                    if (y + 1 < pos.GetLength(1))
                    {
                        if (pos[x, y + 1] == 'E' || pos[x, y + 1] == 'S' || pos[x, y + 1] == '.')
                        {
                            derecha = true;
                        }
                    }

                    // Comprobamos abajo
                    if (x + 1 < pos.GetLength(0))
                    {
                        if (pos[x + 1, y] == 'E' || pos[x + 1, y] == 'S' || pos[x + 1, y] == '.')
                        {
                            abajo = true;
                        }
                    }

                    // Comprobamos izquierda
                    if (y - 1 >= 0)
                    {
                        if (pos[x, y - 1] == 'E' || pos[x, y - 1] == 'S' || pos[x, y - 1] == '.')
                        {
                            izquierda = true;
                        }
                    }

                    // Comprobamos entrada
                    if(pos[x, y] == 'E')
                    {
                        inicio = true;
                    }

                    // Comprobamos salida
                    if (pos[x, y] == 'S')
                    {
                        fin = true;
                    }

                    // Metemos la casilla en el tablero con los parámetros obtenidos
                    tablero[x, y] = new Casilla(x, y, new bool[] { arriba, derecha, abajo, izquierda }, inicio, fin);
                }
            }

            return tablero;
        }

        // Muestra el tablero
        public static void MostrarPosiciones(char[,] posiciones)
        {
            Console.WriteLine();
            for (int x = 0; x < posiciones.GetLength(0); x++)
            {
                for (int y = 0; y < posiciones.GetLength(1); y++)
                {
                    Console.Write(posiciones[x, y] + " ");
                }
                Console.WriteLine();
            }
            Console.WriteLine();
        }

        // Comprobamos el número de entradas y salidas introducidos (máximo 1)
        public static bool ComprobarEntradaSalida(char c, char[,] laberinto)
        {
            bool result = true;
            int count = 0;

            foreach (char posicion in laberinto)
            {
                if (posicion == c)
                {
                    count++;
                }
            }

            if (count > 0)
            {
                result = false;
            }

            return result;
        }

        // Recursividad
        public static void ComprobarCamino(Laberinto laberinto, Casilla casillaActual)
        {

            if (casillaActual.IsFin())
            {
                laberinto.SetCamino(true);
            }
            else
            {
                int posXnueva, posYnueva;
                Casilla casillaNueva;
                int[,] movimientos = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

                for (int i = 0; i < movimientos.GetLength(0); i++)
                {
                    posXnueva = casillaActual.GetPosX() + movimientos[i, 0];
                    posYnueva = casillaActual.GetPosY() + movimientos[i, 1];
                    casillaNueva = laberinto.GetCasillaComprobada(posXnueva, posYnueva);

                    if (laberinto.MovimientoDisponible(casillaActual, casillaNueva, i))
                    {
                        casillaActual.SetVisitado(true);
                        ComprobarCamino(laberinto, casillaNueva);
                        casillaActual.SetVisitado(false);
                    }
                }
            }
        }
    }

    public class Laberinto
    {

        private Casilla[,] tablero;

        private bool camino; // tiene camino = true, no tiene camino = false

        public Laberinto(Casilla[,] tablero)
        {
            this.tablero = tablero;
        }

        // 0 Arriba, 1 Derecha, 2 Abajo, 3 Izquierda
        public bool MovimientoDisponible(Casilla casillaActual, Casilla casillaDestino, int movimiento)
        {

            if (casillaDestino != null && !casillaDestino.IsVisitado())
            {
                return casillaActual.MovimientoDisponible(movimiento);
            }

            return false;
        }

        public Casilla GetCasillaComprobada(int x, int y)
        {
            if (DentroDelLimite(x, y))
            {
                return tablero[x, y];
            }
            return null;
        }

        public bool DentroDelLimite(int x, int y)
        {

            return (x >= 0 && x < tablero.GetLength(0)) && (y >= 0 && y < tablero.GetLength(0));

        }

        public bool GetCamino()
        {
            return camino;
        }

        public void SetCamino(bool camino)
        {
            this.camino = camino;
        }

    }

    public class Casilla
    {

        private int posX;
        private int posY;
        private bool visitado;
        private bool inicio;
        private bool fin;
        private bool[] permitir_paso; // 0 Arriba, 1 Derecha, 2 Abajo, 3 Izquierda

        public Casilla(int posX, int posY)
        {
            this.posX = posX;
            this.posY = posY;
        }

        public Casilla(int posX, int posY, bool[] permitir_paso, bool inicio, bool fin)
        {
            this.posX = posX;
            this.posY = posY;
            this.permitir_paso = permitir_paso;
            this.inicio = inicio;
            this.fin = fin;
        }

        public bool GetPermitirPaso(int pos)
        {
            return permitir_paso[pos];
        }

        public void SetPermitirPaso(bool[] permitir_paso)
        {
            this.permitir_paso = permitir_paso;
        }

        public bool IsInicio()
        {
            return inicio;
        }

        public void SetInicio(bool inicio)
        {
            this.inicio = inicio;
        }

        public bool IsFin()
        {
            return fin;
        }

        public void SetFin(bool fin)
        {
            this.fin = fin;
        }

        // 0 Arriba, 1 Derecha, 2 Abajo, 3 Izquierda
        public bool MovimientoDisponible(int movimiento)
        {
            return GetPermitirPaso(movimiento);
        }

        public int GetPosX()
        {
            return posX;
        }

        public void SetPosX(int posX)
        {
            this.posX = posX;
        }

        public int GetPosY()
        {
            return posY;
        }

        public void SetPosY(int posY)
        {
            this.posY = posY;
        }

        public bool IsVisitado()
        {
            return visitado;
        }

        public void SetVisitado(bool visitado)
        {
            this.visitado = visitado;
        }

    }

}