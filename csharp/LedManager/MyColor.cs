using System;

namespace LedManager{
    internal class MyColor
    {
        private byte[] rgb {get; set;} =  {0, 0, 0};
        public MyColor() => Randomize();
        public MyColor(byte r, byte g, byte b) => this.rgb = new byte[] { r, g, b };
        public void Randomize(){
            Random rnd = new Random();
            byte[] b = new byte[3];
            rnd.NextBytes(b);
            rgb = b;
            /* Console.WriteLine("\n\nRandom color is...");
            for (int i = 0; i <= b.GetUpperBound(0); i++)
            {
                Console.WriteLine("{0}: {1}", i, b[i]);
            } */
        }
        internal byte getRValue() => rgb[0];
        internal byte getGValue() => rgb[1];
        internal byte getBValue() => rgb[2];
    }
}