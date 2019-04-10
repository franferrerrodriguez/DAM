using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

/*
Ferrer Rodríguez, Francisco José
Practica Evaluable Tema 4
Ejercicio 2 - Matriz
Apartado 2.1 si
Apartado 2.2 si
Apartado 2.3 si
Apartado 2.4 si
Apartado 2.5 si
*/
namespace Matriz
{
    class Matriz
    {
        public const int elem = 4;

        static void Main(string[] args)
        {
            int[,] mA = new int[elem, elem];

            for (int i = 0; i < elem; i++)
            {
                for(int a = 0; a < elem; a++)
                {
                    Console.Write("Introduzca número para la posición [{0}, {1}] de la matriz: ",i, a);
                    try
                    {
                        mA[i, a] = Convert.ToInt32(Console.ReadLine());
                    }
                    catch(FormatException e)
                    {
                        Console.WriteLine("\nError. Debe introducir un número entero válido.");
                        a--;
                    }
                }
            }

            Console.WriteLine();

            mostrarMatriz("Matriz mA", mA);
            int[,] mB = transformacion(mA);
            mostrarMatriz("Matriz mB", mB);
            calcularDiagonalPrincipal("mB", mB);
            calcularDiagonalSecundaria("mB", mB);

            Console.Write("Pulse cualquier tecla para salir del programa...");
            Console.ReadKey();
        }

        static int[,] transformacion(int[,] m)
        {
            int[,] nm = new int[elem, elem];
            int x = 0;
            int y;
            for (int i = elem - 1; i >= 0; i--)
            {
                y = 0;
                for (int a = elem - 1; a >= 0; a--)
                {
                    nm[x, y] = m[i, a];
                    y++;
                }
                x++;
            }
            return nm;
        }

        static void mostrarMatriz(String titulo, int[,] m)
        {
            if(titulo != "")
            {
                Console.WriteLine("######## {0} ########", titulo);
            }
            for (int i = 0; i < elem; i++)
            {
                for (int a = 0; a < elem; a++)
                {
                    Console.Write(m[i, a] + "\t");
                }
                Console.WriteLine();
            }
            Console.WriteLine();
        }

        static void calcularDiagonalPrincipal(String titulo, int[,] m)
        {
            String s = "La diagonal principal de " + titulo + " es: ";
            int total = 0;
            int a = 0;
            for (int i = 0; i < elem; i++)
            {
                total += m[a, i];
                s += m[i, a] + " ";
                a++;
            }
            Console.WriteLine(s + "\nSu suma es: " + total + ".\n");
        }

        static void calcularDiagonalSecundaria(String titulo, int[,] m)
        {
            String s = "La diagonal secundaria de " + titulo + " es: ";
            int total = 0;
            int a = 0;
            for (int i = elem - 1; i >= 0; i--)
            {
                total += m[a, i];
                s += m[a, i] + " ";
                a++;
            }
            Console.WriteLine(s + "\nSu suma es: " + total + ".\n");
        }
    }
}