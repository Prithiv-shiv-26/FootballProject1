package com.footballleague.footballleague.controller;

import com.footballleague.footballleague.dto.FixtureDto;
import com.footballleague.footballleague.service.FixtureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/fixtures")
@AllArgsConstructor
public class FixtureController {
    private FixtureService fixtureService;

    @PostMapping
    public ResponseEntity<FixtureDto> createFixture(@RequestBody FixtureDto fixtureDto){
        FixtureDto createdFixture = fixtureService.createFixture(fixtureDto);
        return new ResponseEntity<>(createdFixture, HttpStatus.CREATED);
    }

    @DeleteMapping("/{fixtureId}")
    public ResponseEntity<String> DeleteFixture(@PathVariable("fixtureId") Long fixtureId){
        try {
            fixtureService.DeleteFixture(fixtureId);
            return ResponseEntity.ok("Fixture deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete fixture: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<FixtureDto> updateFixture(@PathVariable("id") Long fixtureId, @RequestBody FixtureDto updatedFixtureDto) {
        FixtureDto updatedFixture = fixtureService.updateFixture(fixtureId, updatedFixtureDto);
        return ResponseEntity.ok(updatedFixture);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FixtureDto> getFixtureById(@PathVariable("id") Long fixtureId) {
        FixtureDto fixtureDto = fixtureService.getFixtureById(fixtureId);
        return ResponseEntity.ok(fixtureDto);
    }

    @GetMapping
    public ResponseEntity<List<FixtureDto>> getAllFixtures() {
        List<FixtureDto> fixtureDtos = fixtureService.getAllFixtures();
        return ResponseEntity.ok(fixtureDtos);
    }
}
