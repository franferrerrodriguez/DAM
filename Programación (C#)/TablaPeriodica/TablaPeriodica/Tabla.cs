using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase que implementa la lógica principal del programa, se trabajará con objetos de tipo Elemento, siglos y años
    /// </summary>
    class Tabla
    {
        public static void MostrarSigloActual(List<Elemento> elementos)
        {
            Console.Clear();
            MostrarSiglo(elementos, 21);
        }

        public static String GetNombreSiglo(int siglo)
        {
            return siglo == 0 ? "Edad Antigua" : "Siglo " + siglo;
        }

        public static void MostrarSiglo(List<Elemento> elementos, int siglo)
        {
            int ano_fin = siglo * 100;
            int ano_inicio = ano_fin - 100;

            Console.WriteLine("{0}\n", GetNombreSiglo(siglo));

            int br = 0;
            for (int ano_actual = ano_inicio; ano_actual < ano_fin; ano_actual++)
            {
                MostrarColorElemento(elementos, ano_actual);
                br++;
                if (br.Equals(5))
                {
                    br = 0;
                    Console.WriteLine();
                }
            }
            Console.WriteLine();
        }

        public static int GetNumeroElementosSiglo(List<Elemento> elementos, int siglo)
        {
            int ano_fin = siglo * 100;
            int ano_inicio = ano_fin - 100;

            int num_elementos = 0;
            for (int ano_actual = ano_inicio; ano_actual < ano_fin; ano_actual++)
                num_elementos += GetNumElementosAno(elementos, ano_actual);

            return num_elementos;
        }

        public static void MostrarTodosSiglos(List<Elemento> elementos)
        {
            Console.Clear();
            for (int siglo = 0; siglo <= 21; siglo++)
                Console.WriteLine(GetNombreSiglo(siglo).PadRight(14, ' ') + ": " + GetNumeroElementosSiglo(elementos, siglo) + " elementos");
        }

        public static void DetalleAnoElemento(List<Elemento> elementos, int ano)
        {
            Boolean existe = false;
            foreach (Elemento elemento in elementos)
                if (elemento.GetAno().Equals(ano))
                {
                    existe = true;
                    Console.SetCursorPosition(40, 1);
                    Console.Write("");
                    Console.SetCursorPosition(40, 2);
                    Console.WriteLine(String.Format("Descubierto {0} ({1}, {2})", elemento.GetNombre(), elemento.GetSimboloQuimico(), elemento.GetNumeroAtomico()));
                    Console.SetCursorPosition(40, 3);
                    Console.Write(elemento.ToString());
                    Console.SetCursorPosition(40, 4);
                    Console.Write("");
                    Console.SetCursorPosition(40, 5);
                    Console.Write("Pulsa una tecla para continuar...");
                    Console.ReadKey();
                }
            if (!existe)
            {
                Console.Clear();
                Console.WriteLine("No existen elementos para el año\nintroducido. Pruebe con otro.");
            }
                
        }

        /*
        Nuevo elemento. Limpiará la pantalla y pedirá al usuario los datos de
        un nuevo elemento, añadiéndolo a la colección. Se deberá verificar sobre la marcha
        que cada dato del elemento sea correcto(que los textos no estén vacíos, la fecha
        sea válida y los datos numéricos
        */
        public static Elemento PedirElemento()
        {
            Elemento elemento = null;
            String[] tipos = {"1. Metal", "2. No Metal", "3. Metaloide"};

            string opcion;
            do
            {
                Console.Clear();
                Console.WriteLine("Elije un tipo:\n");
                foreach (String tipo in tipos)
                    Console.WriteLine(tipo);
                opcion = Console.ReadLine();
                if (opcion.Equals("1") || opcion.Equals("2") || opcion.Equals("3"))
                {
                    String nombre = requestString("Introduce el nombre");
                    String simbolo_quimico = requestString("Introduce el símbolo químico");
                    int numero_atomico = requestInt("Introduce el número atómico");
                    DateTime ano_descubrimiento = requestDateTime("Introduce el año de descubrimiento");
                    Boolean elemento_antiguo = requestBoolean("¿Es un elemento antiguo?");
                    String color;
                    Boolean esLiquido;
                    Boolean esMetalNoble;
                    Boolean esGasNoble;
                    Boolean esMetalRefractario;
                    switch (opcion)
                    {
                        case "1":
                            esLiquido = requestBoolean("¿Es líquido?");
                            color = requestString("Introduce el color");
                            esMetalNoble = requestBoolean("¿Es metal noble?");
                            elemento = new Metal(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo, esLiquido, color, esMetalNoble);
                            break;
                        case "2":
                            esGasNoble = requestBoolean("¿Es gas noble?");
                            elemento = new NoMetal(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo, esGasNoble);
                            break;
                        case "3":
                            esMetalRefractario = requestBoolean("¿Es metal refractario?");
                            esMetalNoble = requestBoolean("¿Es metal noble?");
                            elemento = new Metaloide(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo, esMetalRefractario, esMetalNoble);
                            break;
                    }
                }else
                    opcion = "";
            } while (opcion.Equals(""));

            return elemento;
        }

        public static String requestString(String title)
        {
            String s;
            do
            {
                Console.WriteLine(title);
                s = Console.ReadLine();
            } while (s.Equals(""));
            return s;
        }

        public static int requestInt(String title)
        {
            int _int = -1;
            do
            {
                Console.WriteLine(title);
                try
                {
                    _int = Convert.ToInt32(Console.ReadLine());
                }
                catch (FormatException e)
                {
                    Console.WriteLine("Debe introducir un valor numérico.\n");
                }
            } while (_int == -1);
            return _int;
        }

        public static double requestDouble(String title)
        {
            Double _double = -1.0;
            do
            {
                Console.WriteLine(title);
                try
                {
                    _double = Convert.ToDouble(Console.ReadLine());
                }
                catch (FormatException e)
                {
                    Console.WriteLine("Debe introducir un valor numérico.\n");
                }
            } while (_double == -1.0);
            return _double;
        }

        public static DateTime requestDateTime(String title)
        {
            DateTime date = new DateTime();
            do
            {
                Console.WriteLine(title);
                Console.WriteLine("Formato: (aaaa-mm-dd)");
                try
                {
                    date = Convert.ToDateTime(Console.ReadLine());
                }
                catch (FormatException e)
                {
                    Console.WriteLine("Debe introducir una fecha con el formato correcto: (aaaa-mm-dd).\n");
                }
            } while (date == new DateTime());
            return date;
        }

        public static Boolean requestBoolean(String title)
        {
            String s_boolean;
            Boolean _boolean = false;
            do
            {
                Console.WriteLine(title);
                Console.WriteLine("Introduce (SI/NO)");
                s_boolean = Console.ReadLine();
                if (s_boolean.Equals("SI", StringComparison.OrdinalIgnoreCase))
                    _boolean = true;
                else if (s_boolean.Equals("NO", StringComparison.OrdinalIgnoreCase))
                    _boolean = false;
                else
                {
                    s_boolean = "";
                    Console.WriteLine("Debe introducir el parámetro SI o NO.\n");
                }
            } while (s_boolean.Equals(""));

            return _boolean;
        }

        public static void MostrarColorElemento(List<Elemento> elementos, int ano)
        {
            /*
            - Rojo si se descubrieron solo metales.
            - Azul si se descubrieron solo metaloides.
            - Verde si se descubrieron solo gases.
            - Color invertido(texto negro sobre fondo blanco) en el caso de que en un mismo año o siglo haya más de un tipo de elemento.
            - En color estándar(texto blanco sobre fondo negro), los años o siglos donde no haya nada que mostrar.
            */
            int num_metales = 0;
            int num_metaloides = 0;
            int num_nometales = 0;
            foreach (Elemento e in elementos)
                if (e.GetAno().Equals(ano))
                    if (e.GetType() == typeof(Metal))
                        num_metales++;
                    else if (e.GetType() == typeof(Metaloide))
                        num_metaloides++;
                    else if (e.GetType() == typeof(NoMetal))
                        num_nometales++;

            // Color invertido(texto negro sobre fondo blanco) en el caso de que en un mismo año o siglo haya más de un tipo de elemento
            if (num_metales > 0 && num_metaloides > 0 ||
                num_metales > 0 && num_nometales > 0 ||
                num_metaloides > 0 && num_metales > 0 ||
                num_metaloides > 0 && num_nometales > 0 ||
                num_nometales > 0 && num_metales > 0 ||
                num_nometales > 0 && num_metaloides > 0)
            {

                Console.BackgroundColor = ConsoleColor.Black;
                Console.ForegroundColor = ConsoleColor.White;
            }
            else if (num_metales > 0 && num_metaloides == 0 && num_metaloides == 0) // Rojo si se descubrieron solo metales
                Console.ForegroundColor = ConsoleColor.Red;
            else if (num_metaloides > 0 && num_metales == 0 && num_nometales == 0) // Azul si se descubrieron solo metaloides
                Console.ForegroundColor = ConsoleColor.Blue;
            else if (num_nometales > 0 && num_metales == 0 && num_metaloides == 0) // Verde si se descubrieron solo gases
                Console.ForegroundColor = ConsoleColor.Green;

            //En color estándar(texto blanco sobre fondo negro), los años o siglos donde no haya nada que mostrar

            Console.Write(ano);

            // Reseteamos colores
            Console.BackgroundColor = ConsoleColor.White;
            Console.ForegroundColor = ConsoleColor.Black;

            Console.Write(" ");
        }

        public static int GetNumElementosAno(List<Elemento> elementos, int ano)
        {
            int num_elementos = 0;
            foreach (Elemento elemento in elementos)
                if (elemento.GetAno().Equals(ano) && (elemento.GetType() == typeof(Metal) || elemento.GetType() == typeof(Metaloide) || elemento.GetType() == typeof(NoMetal)))
                    num_elementos++;
            return num_elementos;
        }

    }
}