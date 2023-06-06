package cl.mobdev.rickandmorty.mocks;

import cl.mobdev.rickandmorty.TestUtil;
import cl.mobdev.rickandmorty.domain.model.Character;
import cl.mobdev.rickandmorty.domain.model.Location;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Collection;
import java.util.List;

public class CharacterMock {

  public static Collection<Character> build_characters_without_locations() {
    String jsonFile = "charactersWithoutLocations.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Character> build_characters_with_origins_and_locations() {
    String jsonFile = "charactersWithOriginAndLocations.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Location> build_locations_only() {
    String jsonFile = "locationsOnly.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Location>>() {
    });
  }

  public static Character build_character_only() {
    String jsonFile = "CharacterOnly.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_character_with_location() {
    String jsonFile = "CharacterWithLocation.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_character_specie_alien() {
    String jsonFile = "characterSpecieAlien.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_character_only_origin_empty() {
    String jsonFile = "CharacterEmptyOrigin.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_character_three() {
    String jsonFile = "character_three.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_character_with_origin_location_unknown() {
    String jsonFile = "character_unknown_origin_and_location.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_character_with_location_unknown() {
    String jsonFile = "character_location_unknown.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Collection<Character> build_characters_with_planet_mars() {
    String jsonFile = "characterWithPlanetMars.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Character> build_characters_with_alien() {
    String jsonFile = "charactersWithAlien.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Character> build_characters_without_id_alien() {
    String jsonFile = "charactersWithoutIdWithAliens.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }


  public static Collection<Character> build_characters_with_planet_mars_character_name_empty() {
    String jsonFile = "charactersWithPlanetMarsNameEmpty.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }


  public static Collection<Character> build_from_repository() {
    String jsonFile = "charactersFromRepository.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Character build_character_api() {
    String jsonFile = "characterApi.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_compare_entity() {
    String jsonFile = "characterCompareMapper.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_location_null_mapper() {
    String jsonFile = "character_with_location_null_mapper.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_origin_null_mapper() {
    String jsonFile = "character_with_origin_null_mapper.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static Character build_insert_character() {
    String jsonFile = "character_to_insert.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Character>() {
    });
  }

  public static List<Character> listCharacters() {
    String jsonFile = "listCharacters.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<Character>>() {
    });
  }

  public static List<Character> listCharactersWithOriginAndLocationNull() {
    String jsonFile = "listCharactersWithLocationOriginNull.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<Character>>() {
    });
  }

  public static List<Character> listCharactersWithOriginAndLocationNullImpl() {
    String jsonFile = "listCharactersWithLocationOriginNullImpl.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<List<Character>>() {
    });
  }

  public static Collection<Location> build_locations_alive() {
    String jsonFile = "locationsOnlyAlive.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Location>>() {
    });
  }

  public static Collection<Character> buildCharactersWithLocationAlive() {
    String jsonFile = "charactersWithLocationAlive.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }

  public static Collection<Character> buildCharactersWithLocationAliveResponseBd() {
    String jsonFile = "charactersWithLocationAliveResponseBd.json";
    return TestUtil.buildObjectFromFile(jsonFile, new TypeReference<Collection<Character>>() {
    });
  }


}
