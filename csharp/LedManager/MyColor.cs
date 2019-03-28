using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Windows;
using System.Drawing;
using System.ComponentModel;
using System.Data;
using System.IO;

namespace LedManager
{
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
        public MyColor PickColor(string path, byte coordX, byte coordY)
        {
            MyColor myColor = new MyColor();
            byte[] b = new byte[3];

            // Create a Bitmap object from an image file.
            Bitmap myBitmap = new Bitmap(path);

            // Get the color of a pixel within myBitmap.
            System.Drawing.Color pixelColor = myBitmap.GetPixel(coordX, coordY);

            b[0] = pixelColor.R;
            b[1] = pixelColor.G;
            b[2] = pixelColor.B;

            myColor.rgb = b;

            return myColor;
        }
    }
}