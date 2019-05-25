using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TablaPeriodica
{
    /// <summary>
    /// Clase Metal heredada de la clase Elemento, será uno de los subtipos esta clase y tendrá sus atributos no comunes
    /// </summary>
    class Metal : Elemento
    {
        private bool esLiquido;
        private string color;
        private bool esMetalNoble;

        public Metal(string nombre, string simbolo_quimico, int numero_atomico, DateTime ano_descubrimiento, bool elemento_antiguo, bool esLiquido, string color, bool esMetalNoble) : base(nombre, simbolo_quimico, numero_atomico, ano_descubrimiento, elemento_antiguo)
        {
            this.esLiquido = esLiquido;
            this.color = color;
            this.esMetalNoble = esMetalNoble;
        }

        public bool EsLiquido()
        {
            return esLiquido;
        }

        public void SetLiquido(bool esLiquido)
        {
            this.esLiquido = esLiquido;
        }

        public string GetColor()
        {
            return color;
        }

        public void SetColor(string color)
        {
            this.color = color;
        }

        public bool EsMetalNoble()
        {
            return esMetalNoble;
        }

        public void SetMetalNoble(bool esMetalNoble)
        {
            this.esMetalNoble = esMetalNoble;
        }

        public String ToString()
        {
            return string.Format("Liquido: {0} - Color: {1} - Noble: {2}", EsLiquido() ? "Sí" : "No", GetColor(), EsMetalNoble() ? "Sí" : "No");
        }

        public override String ToCsv()
        {
            return string.Format("{0};{1};{2};{3}", base.ToCsv(), EsLiquido() ? "liquido" : "no", GetColor(), EsMetalNoble() ? "noble" : "no noble");
        }

    }
}