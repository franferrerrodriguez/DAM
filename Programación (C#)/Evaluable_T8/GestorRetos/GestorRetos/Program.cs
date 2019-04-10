using System;
using System.Collections.Generic;
using System.Linq;

/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 8
Apartado 1 si
Apartado 2 si
Apartado 3 si
Apartado 4 si
*/
namespace GestorRetos
{
    class MainClass
    {
        public static void Main(string[] args)
        {
            CatalogoRetos catalogo = new CatalogoRetos();
            Reto reto;
            string reto_text;
            do
            {
                reto_text = Console.ReadLine();
                reto = Reto.TextToReto(reto_text);

                if (null != reto)
                    catalogo.NuevoReto(reto);
                else
                    Console.WriteLine("Algún parámetro introducido no es válido");
            } while (reto_text != "");

            List<Usuario> usuarios = new List<Usuario>
            {
                new Alumno("Francisco", "fran@fran.com", "1DAM"),
                new Alumno("Maria", "maria@maria.com", "1DAM"),
                new Alumno("Pedro", "pedro@pedro.com", "2DAW"),
                new Profesor("Antonio", "antonio@antonio.com", "Java"),
                new Profesor("Alberto", "alberto@alberto.com", "C#")
            };

            string menu;
            do
            {
                Console.WriteLine("1. Salir del programa");
                Console.WriteLine("2. Buscar reto");
                Console.WriteLine("3. Listar por dificultad");
                Console.WriteLine("4. Resolver reto");

                menu = Console.ReadLine();
                switch (menu)
                {
                    case "2":
                        reto = catalogo.PedirReto();
                        if (null != reto)
                            reto.Imprimir();
                        else
                            Console.WriteLine("Reto no encontrado\n");

                        break;
                    case "3":
                        Console.Write("Introduce un nivel de dificultad: ");

                        if (Int32.TryParse(Console.ReadLine(), out int dificultad) && Reto.DificultadValida(dificultad))
                            catalogo.ListarPorDificultad(dificultad);
                        else
                            Console.WriteLine("Debe introducir una dificultad válida\n");

                        break;
                    case "4":
                        Console.Write("Introduce un nickname de usuario: ");

                        string nickname = Console.ReadLine();
                        if (Usuario.CountByNickName(usuarios, nickname))
                        {
                            reto = catalogo.PedirReto();
                            if (null != reto)
                            {
                                usuarios.FirstOrDefault(x => x.GetNickName().Equals(nickname)).SetRetoResuelto(reto);
                                Console.WriteLine("Reto añadido correctamente al usuario: {0}\n", nickname);
                            }

                            else
                                Console.WriteLine("Reto no encontrado\n");
                        }
                        else
                            Console.WriteLine("Usuario no encontrado\n");

                        break;
                }
            } while (menu != "1");

            foreach(Usuario usuario in usuarios)
            {
                usuario.Imprimir();
            }

            Console.ReadKey();
        }
    }
}