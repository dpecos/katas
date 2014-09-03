module.exports = function(grunt) {

   // Project configuration.
   grunt.initConfig({
      coffee: {
         app: {
            src: ['src/**/*.coffee','spec/**/*.coffee'],
            dest: 'build',
            options: {
               bare: true,
               preserve_dirs: true
            }
         }
      },
      jasmine : {
         src : 'build/src/**/*.js',
         specs : 'build/spec/**/*.js'
      },
      coffeelint: {
         app: {
            files: {
               src: ['src/**/*.coffee']
            },
            options: {
               "max_line_length": {
                  "level": "ignore"
               }
            }
         },

         tests: {
            files: {
               src: ['specs/**/*.coffee']
            },
            options: {
               "no_trailing_whitespace": {
                  "level": "error"
               }
            }
         }
      },
      watch: {
         // setup watch tasks. anytime a file is changed, run respective task
         coffee: {
            files: ['<config:coffee.app.src>'],
            tasks: 'default'
         },
         jasmine: {
            files: ['<config:jasmine.specs>'], 
            tasks: 'default'
         }
      }
   });

   grunt.loadNpmTasks('grunt-jasmine-runner');
   grunt.loadNpmTasks('grunt-coffee');
   grunt.loadNpmTasks('grunt-coffeelint');


   // Default task.
   grunt.registerTask('default', 'coffeelint coffee jasmine');

};
