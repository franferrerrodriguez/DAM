using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase principal del programa donde estará el menú y las llamadas a la lógica de otras clases
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            Console.BackgroundColor = ConsoleColor.White;
            Console.ForegroundColor = ConsoleColor.Black;
            Console.Clear();

            GestorElementos gestorElementos = new GestorElementos();

            Tabla.MostrarSigloActual(gestorElementos.GetElementos());

            string opcion;
            do
            {
                MostrarMenu();

                opcion = Console.ReadLine();

                switch (opcion)
                {
                    case "1":
                        // Mostramos todos los siglos
                        Tabla.MostrarTodosSiglos(gestorElementos.GetElementos());
                        break;
                    case "2":
                        int siglo;
                        do
                        {
                            // CUANDO PONEMOS LETRAS EN EL SIGLO ENTRA
                            // Pedimos el siglo
                            Console.SetCursorPosition(40, 6);
                            Console.Write("Indica el siglo: ");

                            if (Int32.TryParse(Console.ReadLine(), out siglo))
                                if (siglo < 0 || siglo > 21)
                                    siglo = -1;
                            if(siglo < 0)
                                // Limpiamos lo escrito para volver a pedir
                                LimpiarLinea(57, 6);
                        } while (siglo < 0);

                        // Limpiamos consola y mostramos el siglo introducido
                        Console.Clear();
                        Tabla.MostrarSiglo(gestorElementos.GetElementos(), siglo);

                        // Pedimos el siglo
                        Console.SetCursorPosition(40, 0);
                        Console.Write("Elige un año: ");

                        if (Int32.TryParse(Console.ReadLine(), out int ano))
                            Tabla.DetalleAnoElemento(gestorElementos.GetElementos(), ano);
                                break;
                    case "3":
                        // Pedimos y guardamos nuevo elemento
                        gestorElementos.SetElemento(Tabla.PedirElemento());
                        break;
                    case "4":
                        // Guardamos elementos y salimos del programa
                        Console.Clear();
                        GestorElementos.Guardar(gestorElementos.GetElementos());
                        Console.WriteLine("Fin del programa.");
                        break;
                    default:
                        // Limpiamos lo escrito para volver a pedir
                        LimpiarLinea(58, 5);
                        break;
                }
            } while (!opcion.Equals("4"));

            Console.ReadKey();
        }

        public static void MostrarMenu()
        {
            string[] array_menu = { "1. Ver todos los siglos\n", "2. Ver siglo\n", "3. Nuevo Elemento\n", "4. Salir\n", "", "Elige una opción: " };
            for (int i = 0; i < array_menu.Length; i++)
            {
                LimpiarLinea(40, i);
                Console.SetCursorPosition(40, i);
                Console.Write(array_menu[i]);
            }
        }

        public static void LimpiarLinea(int x, int y)
        {
            for (int i = x; i < 100; i++)
            {
                Console.SetCursorPosition(i, y);
                Console.WriteLine(" ");
            }
        }

    }
}