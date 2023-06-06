package cl.mobdev.rickandmorty.api;

import cl.mobdev.rickandmorty.api.model.ApiCharacter;
import cl.mobdev.rickandmorty.api.model.ApiCharacterDb;
import cl.mobdev.rickandmorty.api.model.ApiErrorResponse;
import cl.mobdev.rickandmorty.api.model.GetCharacterResponse;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;

@Api(value = "character", description = "the character API")
public interface CharacterApi {

    @ApiOperation(value = "", notes = "Save a Character", response = Void.class, tags={ "character", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "A successful status", response = Void.class),
        @ApiResponse(code = 500, message = "Response with error.", response = Void.class) })
    @RequestMapping(value = "/character",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> characterPost(@ApiParam(value = "Character to save."  ) @RequestBody ApiCharacter character);


    @ApiOperation(value = "", notes = "Delete Character Repository", response = Void.class, tags={ "character", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Reponse Ok delete", response = Void.class),
        @ApiResponse(code = 404, message = "Response with error.", response = Void.class),
        @ApiResponse(code = 500, message = "Response with error.", response = Void.class) })
    @RequestMapping(value = "/character/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCharacter(@ApiParam(value = "",required=true ) @PathVariable("id") String id);


    @ApiOperation(value = "", notes = "All Characters to save", response = ApiCharacterDb.class, responseContainer = "List", tags={ "character", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A successful status", response = ApiCharacterDb.class),
        @ApiResponse(code = 500, message = "Response with error.", response = ApiCharacterDb.class) })
    @RequestMapping(value = "/character",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<ApiCharacterDb>> getAllCharacter();


    @ApiOperation(value = "", notes = "Return Object Character", response = GetCharacterResponse.class, tags={ "character", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of characters.", response = GetCharacterResponse.class),
        @ApiResponse(code = 404, message = "Response with error.", response = GetCharacterResponse.class),
        @ApiResponse(code = 500, message = "Response with error.", response = GetCharacterResponse.class) })
    @RequestMapping(value = "/character/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<GetCharacterResponse> getCharacter(@ApiParam(value = "",required=true ) @PathVariable("id") Integer id);


    @ApiOperation(value = "", notes = "Return status success", response = Void.class, tags={ "character", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "A list of characters from Gateway, save all in reposository by status", response = Void.class),
        @ApiResponse(code = 404, message = "Response with error.", response = Void.class),
        @ApiResponse(code = 500, message = "Response with error.", response = Void.class) })
    @RequestMapping(value = "/character/status/{status}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Void> saveAllCharacterInRepositoryFromGateway(@ApiParam(value = "",required=true ) @PathVariable("status") String status);

}
