using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase que trabaja principalmente con objetos de tipo Elemento, para realizar alguna lógica con ellos
    /// </summary>
    class GestorElementos
    {
        private const string ruta_fichero = "elementos.txt";
        private const char delimitador = ';';
        private List<Elemento> elementos;

        public GestorElementos() {
            elementos = Cargar();
            Guardar(elementos);
        }

        public static List<Elemento> Cargar()
        {
            List<Elemento> elementos = new List<Elemento>();

            // Comprobamos si el fichero existe en la ruta indicada
            if (File.Exists(ruta_fichero))
            {
                StreamReader fichero = File.OpenText(ruta_fichero);

                using (StreamReader rd = new StreamReader(ruta_fichero, Encoding.Default))
                {
                    // Recorremos las líneas del fichero
                    string linea;
                    int num_linea = 1;
                    do
                    {
                        linea = rd.ReadLine();
                        if (linea != null)
                        {
                            try
                            {
                                // Realizamos un split por el delim ";"
                                string[] elem_split = linea.Split(delimitador);

                                // Extraemos y mapeamos todos los atributos
                                string nombre = elem_split[1];
                                string simbolo_quimico = elem_split[2];
                                Int32.TryParse(elem_split[3], out int numero_atomico);
                                DateTime ano_descubrimiento = new DateTime();
                                if (Int32.TryParse(elem_split[4], out int ano))
                                    ano_descubrimiento = ano_descubrimiento.AddYears(ano - 1) ;
                                bool elemento_antiguo = elem_split[4] == "A.C." ? true : false;
                                bool esLiquido;
                                string color;
                                bool esMetalNoble;
                                bool esMetalRefractario;
                                bool esGasNoble;

                                switch (elem_split[0])
                                {
                                    case nameof(Metal):
                                        esLiquido = elem_split[5] == "liquido" ? true : false;
                                        color = elem_split[6];
                                        esMetalNoble = elem_split[7] == "noble" ? true : false;
                                        elementos.Add(new Metal(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo, esLiquido, color, esMetalNoble));
                                        break;
                                    case nameof(Metaloide):
                                        esMetalRefractario = elem_split[5] == "si" ? true : false;
                                        esMetalNoble = elem_split[6] == "noble" ? true : false;
                                        elementos.Add(new Metaloide(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo, esMetalRefractario, esMetalNoble));
                                        break;
                                    case nameof(NoMetal):
                                        esGasNoble = elem_split[5] == "gas noble" ? true : false;
                                        elementos.Add(new NoMetal(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo, esGasNoble));
                                        break;
                                }
                            } catch(IndexOutOfRangeException e)
                            {
                                Console.WriteLine("Ha ocurrido un error al leer el fichero {0} en la línea: {1}.\n", ruta_fichero, num_linea);
                            }
                        }
                        num_linea++;
                    } while (linea != null);
                }

                fichero.Close();
            }

            return elementos;
        }

        public static void Guardar(List<Elemento> elementos)
        {
            Console.Clear();

            StreamWriter fichero = new StreamWriter(new FileStream(ruta_fichero, FileMode.Open, FileAccess.ReadWrite), Encoding.UTF8);

            foreach (Elemento e in elementos)
                fichero.WriteLine(e.ToCsv());

            fichero.Close();
        }

        public List<Elemento> GetElementos()
        {
            return elementos;
        }

        public void SetElemento(Elemento elemento)
        {
            elementos.Add(elemento);
        }

    }
}