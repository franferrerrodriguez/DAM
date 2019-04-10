using System;

/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 5
Ejercicio 1
Apartado 1.1 si
Apartado 1.2 si
Apartado 1.3 si
Apartado 1.4 si
Apartado 1.5 si
Apartado 1.6 si
Apartado 1.7 si
*/
namespace AmigoInvisible
{
    class Program
    {
        public struct pareja
        {
            public string nombre_regalador;
            public string nombre_destinatario;
        }

        static void Main(string[] args)
        {
            pareja[] parejas;
            int presupuesto;
            if (args.Length == 0 || args.Length > 1 || !Int32.TryParse(args[0], out presupuesto))
            {
                Console.WriteLine("No se ha indicado el presupuesto.");
            }
            else
            {
                Console.WriteLine("Introduce la lista de nombres:");
                // lista = Console.ReadLine();
                string lista = "Juan,Mari Carmen, Ana, José Enrique, Guillermo";
                string[] array_nombres = lista.Split(',');

                do
                {
                    parejas = GenerarCombinacion(array_nombres);
                } while (!ValidarCombinacion(parejas));

                Console.WriteLine("Combinación generada.");

                bool s = false;
                string nombre;
                string resultado;
                while (!s)
                {
                    Console.WriteLine("Indica el nombre cuyo amigo invible quieres conocer:");
                    nombre = Console.ReadLine();
                    if(nombre == "")
                    {
                        s = true;
                    }
                    BuscarAmigo(nombre, parejas, out resultado);
                    Console.WriteLine(resultado);
                }

                Console.WriteLine("Fin del programa.");

                Console.ReadKey();
            }

        }

        public static pareja[] GenerarCombinacion(string[] nombres)
        {
            pareja[] parejas = new pareja[nombres.Length];
            int i = 0;
            Random r = new Random();
            foreach (string nombre in nombres)
            {
                int random = r.Next(0, nombres.Length);
                parejas[i].nombre_regalador = nombre.Trim();
                parejas[i].nombre_destinatario = nombres[random].Trim();
                i++;
            }

            return parejas;
        }

        public static bool ValidarCombinacion(pareja[] parejas)
        {
            bool result = true;

            foreach (pareja pareja in parejas)
            {
                if(pareja.nombre_regalador == pareja.nombre_destinatario)
                {
                    result = false;
                }

                foreach (pareja p in parejas)
                {
                    if(pareja.nombre_regalador != p.nombre_regalador && pareja.nombre_destinatario == p.nombre_destinatario)
                    {
                        result = false;
                    }
                }
            }

            return result;
        }

        public static void BuscarAmigo(string nombre, pareja[] parejas, out string resultado)
        {
            resultado = "";
            foreach(pareja pareja in parejas)
            {
                if(string.Equals(pareja.nombre_regalador, nombre, StringComparison.OrdinalIgnoreCase))
                {
                    resultado = String.Format("{0}, tu amigo invisible es {1}. El presupuesto es de {2} Euros.\n", pareja.nombre_regalador, pareja.nombre_destinatario, 30);
                }
            }
            if(resultado == "")
            {
                Console.WriteLine(String.Format("No se ha encontrado el nombre {0}.", nombre));
            }
        }

    }
}
