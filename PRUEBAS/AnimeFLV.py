from animeflv import AnimeFLV

def main():
    query = input("🔍 Buscar anime: ")
    
    with AnimeFLV() as api:  # Usa AnimeFLV como contexto
        try:
            # Buscar animes
            results = api.search(query)
            if not results:
                print("❌ No se encontraron resultados.")
                return

            # Mostrar resultados
            print("\n📜 Resultados encontrados:")
            for i, anime in enumerate(results):
                print(f"{i}. {anime.title}")

            # Seleccionar anime
            selection = int(input("\n👉 Elige un anime (número): "))
            selected_anime = results[selection]

            # Obtener info del anime (incluyendo episodios)
            info = api.get_anime_info(selected_anime.id)
            print(f"\n📌 Seleccionaste: {info.title}")

            # Mostrar episodios (del más nuevo al más viejo)
            info.episodes.reverse()
            print("\n🎬 Episodios disponibles:")
            for i, episode in enumerate(info.episodes):
                print(f"{i}. Episodio {episode.id}")

            # Seleccionar episodio
            episode_choice = int(input("\n👉 Elige un episodio (número): "))
            selected_episode = info.episodes[episode_choice]

            # Obtener enlaces de descarga
            print(f"\n🔗 Obteniendo enlaces para Episodio {selected_episode.id}...")
            download_links = api.get_links(selected_anime.id, selected_episode.id)

            # Mostrar enlaces
            print("\n💾 Enlaces de descarga:")
            for link in download_links:
                print(f"🔹 {link.server} → {link.url}")

        except Exception as e:
            print(f"❌ Error: {e}")

if __name__ == "__main__":
    main()