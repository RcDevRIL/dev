using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System;

namespace HitTheTarget
{
    /// <summary>
    /// This is the main type for your game.
    /// </summary>
    public class Game1 : Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;

        SpriteFont gameFont;
        Texture2D target_sprite;
        Texture2D crosshairs_sprite;
        Texture2D background_sprite;

        Vector2 targetPosition = new Vector2(300,300);

        MouseState mouseState;
        const int TARGET_RADIUS = 45;
        int x;
        int y;

        int score = 0;
        float timer = 10f;
        float mouseTargetDist;
        bool mReleased = true;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
            IsMouseVisible = true;
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            target_sprite = Content.Load<Texture2D>("target");
            crosshairs_sprite = Content.Load<Texture2D>("crosshairs");
            background_sprite = Content.Load<Texture2D>("sky");

            gameFont = Content.Load<SpriteFont>("galleryFont");
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// game-specific content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();

            if (timer > 0)
            {
                timer -= (float)gameTime.ElapsedGameTime.TotalSeconds;
                mouseTargetDist = Vector2.Distance(targetPosition, new Vector2(mouseState.X, mouseState.Y));
                if (mouseState.LeftButton == ButtonState.Released) mReleased = true;
                //if (Mouse.GetState().LeftButton.ToString() == "Pressed")
                if (mouseState.LeftButton == ButtonState.Pressed && mouseTargetDist < TARGET_RADIUS && mReleased == true)
                {
                    Scored_Click();
                }

            }

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            spriteBatch.Begin();
            spriteBatch.Draw(background_sprite, new Vector2(0, 0), Color.White);

            if (timer > 0)
            {
                spriteBatch.Draw(target_sprite, new Vector2(targetPosition.X, targetPosition.Y), Color.White);

            }


            spriteBatch.DrawString(gameFont, "Score : " + score.ToString(), new Vector2(3, 3), Color.White);
            spriteBatch.DrawString(gameFont, "Temps : " + Math.Ceiling(timer).ToString(), new Vector2(500, 3), Color.White);

            spriteBatch.End();

            base.Draw(gameTime);
        }

        private void Scored_Click()
        {
            score++;
            Random randX = new Random();
            x = randX.Next(0, 800);
            Random randY = new Random();
            y = randX.Next(50, 400);

            mReleased = false;
            randX = null;
            randY = null;
        }
    }
}
