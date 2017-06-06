import psycopg2
import faker
import random
import string

connection_string = "dbname='testdb' user='lieroz' host='localhost' password='b769sz7u'"


def generate_random_string(length):
	return ''.join(random.choice(string.ascii_lowercase) for i in range(length))


class Filler:
	def __init__(self):
		self.connection = None
		self.fake = faker.Factory.create("en_US")


class UserAndRoleFiller(Filler):
	def __init__(self):
		super().__init__()
		self.fill_users_statement = \
			"INSERT INTO users (username, email, password) VALUES (%(username)s, %(email)s, %(password)s)"
		self.fill_roles_statement = \
			"INSERT INTO roles (role, username) VALUES (%(role)s, %(username)s)"

	def generate_users(self, count):
		users = []
		for i in range(count):
			users.append(
				{
					"username": self.fake.user_name(),
					"email": self.fake.email(),
					"password": self.fake.password(
						length=10, special_chars=True, digits=True, upper_case=True, lower_case=True
					)
				}
			)
		return users

	@staticmethod
	def generate_roles(count, users):
		random.shuffle(users)
		roles = []
		for i in range(count):
			roles.append(
				{
					"role": "MODERATOR",
					"username": users[i]["username"]
				}
			)
		return roles

	def execute_insert(self):
		try:
			self.connection = psycopg2.connect(connection_string)
			cursor = self.connection.cursor()
			users = self.generate_users(10)
			cursor.executemany(self.fill_users_statement, users)
			cursor.executemany(self.fill_roles_statement, self.generate_roles(3, users))
			self.connection.commit()
		except psycopg2.DatabaseError as error:
			if self.connection:
				self.connection.rollback()
			print("Error %s" % error)
		finally:
			if self.connection:
				self.connection.close()


class DictionaryFiller(Filler):
	def __init__(self):
		super().__init__()
		self.fill_languages_statement = \
			"INSERT INTO languages (language, discription) VALUES (%(language)s, %(discription)s)"
		self.fill_dialects_statement = \
			"INSERT INTO dialects (dialect, language, discription) VALUES (%(dialect)s, %(language)s, %(discription)s)"
		self.fill_slangs_statement = "INSERT INTO slangs (slang, discription) VALUES (%(slang)s, %(discription)s)"
		self.fill_obj_files_statement = "INSERT INTO objfiles (name) VALUES (%(name)s)"
		self.fill_symbols_statement = \
			"INSERT INTO symbols (symbol, dialect, file_id, discription) " \
			"VALUES (%(symbol)s, %(dialect)s, %(file_id)s, %(discription)s)"
		self.fill_words_statement = \
			"INSERT INTO words (word, dialect, slang, file_id, discription) " \
			"VALUES(%(word)s, %(dialect)s, %(slang)s, %(file_id)s, %(discription)s)"

	def generate_languages(self, count):
		languages = []
		for i in range(count):
			languages.append(
				{
					"language": generate_random_string(random.randint(4, 9)),
					"discription": self.fake.sentence(nb_words=2, variable_nb_words=True)
				}
			)
		return languages

	def generate_dialects(self, count, languages):
		random.shuffle(languages)
		dialects = []
		for i in range(count):
			dialects.append(
				{
					"dialect": generate_random_string(random.randint(3, 6)),
					"language": languages[random.randint(0, len(languages) - 1)]["language"],
					"discription": self.fake.sentence(nb_words=3, variable_nb_words=True)
				}
			)
		return dialects

	def generate_slangs(self, count):
		slangs = []
		for i in range(count):
			slangs.append(
				{
					"slang": generate_random_string(random.randint(5, 8)),
					"discription": self.fake.sentence(nb_words=3, variable_nb_words=True)
				}
			)
		return slangs

	def generate_obj_files(self, count):
		obj_files = []
		for i in range(count):
			obj_files.append(
				{
					"name": self.fake.word()
				}
			)
		return obj_files

	def generate_symbols(self, count, dialects, number):
		symbols = []
		for i in range(count):
			symbols.append(
				{
					"symbol": generate_random_string(random.randint(2, 3)),
					"dialect": dialects[random.randint(0, len(dialects) - 1)]["dialect"],
					"file_id": random.randint(number + 1, number + len(dialects) - 1),
					"discription": self.fake.sentence(nb_words=3, variable_nb_words=True)
				}
			)
		return symbols

	def generate_words(self, count, dialects, slangs, number):
		words = []
		for i in range(count):
			words.append(
				{
					"word": generate_random_string(random.randint(5, 7)),
					"dialect": dialects[random.randint(0, len(dialects) - 1)]["dialect"],
					"slang": slangs[random.randint(0, len(slangs) - 1)]["slang"],
					"file_id": random.randint(number + 1, number + len(dialects) - 1),
					"discription": self.fake.sentence(nb_words=3, variable_nb_words=True)
				}
			)
		return words

	def execute_insert(self):
		try:
			self.connection = psycopg2.connect(connection_string)
			cursor = self.connection.cursor()
			cursor.execute("SELECT nextval('objfiles_id_seq')")
			number = cursor.fetchone()[0]
			languages = self.generate_languages(5)
			dialects = self.generate_dialects(10, languages)
			slangs = self.generate_slangs(10)
			obj_files = self.generate_obj_files(20)
			symbols = self.generate_symbols(30, dialects, number)
			words = self.generate_words(50, dialects, slangs, number)
			cursor.executemany(self.fill_languages_statement, languages)
			cursor.executemany(self.fill_dialects_statement, dialects)
			cursor.executemany(self.fill_slangs_statement, slangs)
			cursor.executemany(self.fill_obj_files_statement, obj_files)
			cursor.executemany(self.fill_symbols_statement, symbols)
			cursor.executemany(self.fill_words_statement, words)
			self.connection.commit()
		except psycopg2.DatabaseError as error:
			if self.connection:
				self.connection.rollback()
			print("Error %s" % error)
		finally:
			if self.connection:
				self.connection.close()


def main():
	UserAndRoleFiller().execute_insert()
	DictionaryFiller().execute_insert()


if __name__ == "__main__":
	main()
