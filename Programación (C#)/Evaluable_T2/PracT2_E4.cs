/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 2
Ejercicio 4 (PracT2_E4.cs)
*/

using System;

namespace PracT2_E4
{
    class PracT2_E4
    {
        static void Main(string[] args)
        {
            String nombre;
            int nhabitantes;
            int superficie;
            String unidad = "";
            String[] unidades = { "K", "H", "m" };
            
            for (;;)
            {
                Console.WriteLine("¿Cual es el nombre de la región o población? ");
                nombre = Console.ReadLine();

                Console.WriteLine("¿Cuantos habitantes tiene? ");
                nhabitantes = Convert.ToInt32(Console.ReadLine());

                Console.WriteLine("¿Cual es la superficie en m2 de Alicante? ");
                superficie = Convert.ToInt32(Console.ReadLine());

                while (Array.IndexOf(unidades, unidad) < 0)
                {
                    Console.WriteLine("¿Qué unidad de densidad de población deseas utilizar ('FIN' para finalizar)?");
                    Console.WriteLine("K. habitantes/Km2");
                    Console.WriteLine("H. habitantes/Hm2");
                    Console.WriteLine("m. habitantes/m2");
                    unidad = Console.ReadLine();
                }

                if (unidad.Equals("FIN"))
                {
                    Console.WriteLine("Adios!!");
                    break;
                }

                mostrarResultado(nombre, nhabitantes, superficie, unidad);
            }
        }

        private static void mostrarResultado(String nombre, int nhabitantes, int superficie, String unidad)
        {
            try
            {
                switch (unidad)
                {
                    case "K":
                        Console.WriteLine(
                            String.Format(
                                "La densidad de población de {0} es de {1} h/Km2.\n\n",
                                nombre,
                                Convert.ToInt32(nhabitantes / ((double)superficie / 1000000))
                            )
                        );
                        break;
                    case "H":
                        Console.WriteLine(
                            String.Format(
                                "La densidad de población de {0} es de {1} h/Hm2.\n\n",
                                nombre,
                                Convert.ToInt32(nhabitantes / ((double)superficie / 10000))
                            )
                        );
                        break;
                    case "m":
                        Console.WriteLine(
                            String.Format(
                                "La densidad de población de {0} es de {1} h/m2.\n\n",
                                nombre,
                                nhabitantes / superficie
                            )
                        );
                        break;
                }
            }
            catch(DivideByZeroException)
            {
                Console.WriteLine("Error: División por cero.\n\n");
            }
        }

    }
}